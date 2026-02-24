#!/bin/bash
#
# clean-backups.sh — 백업 파일(*.class_YYYYMMDD) 정리 스크립트
#
# 사용법:
#   ./clean-backups.sh --dir ./WAS/jeus/mhcweb/WEB-INF/classes   # dry-run (필수: 대상 디렉토리)
#   ./clean-backups.sh --dir ./classes --execute                  # 실제 삭제 실행
#   ./clean-backups.sh --dir ./classes --execute --keep 0         # 모든 백업 삭제
#   ./clean-backups.sh --dir ./classes --before 20250101          # 날짜 필터
#   ./clean-backups.sh --dir ./classes --module pm                # 특정 모듈만
#   ./clean-backups.sh --dir ./classes --report                   # 현황 보고만

set -euo pipefail

BASE_DIR="$(cd "$(dirname "$0")" && pwd)"
TARGET_DIR=""

# 기본값
DRY_RUN=true
BEFORE=""
KEEP=1
MODULE=""
REPORT_ONLY=false

# 색상
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m'

usage() {
    echo "사용법: $0 [옵션]"
    echo ""
    echo "옵션:"
    echo "  --dir PATH         대상 디렉토리 (필수)"
    echo "  --execute          실제 삭제 실행 (기본: dry-run)"
    echo "  --before YYYYMMDD  지정 날짜 이전 백업만 대상"
    echo "  --keep N           파일당 최신 N개 보존 (기본: 1)"
    echo "  --module NAME      특정 모듈만 대상 (cm,pm,sm,ms,gn,mr,st)"
    echo "  --report           현황 보고만 (삭제 대상 계산 안 함)"
    echo "  -h, --help         도움말"
    exit 0
}

# 인자 파싱
while [ $# -gt 0 ]; do
    case $1 in
        --dir)      TARGET_DIR="$2"; shift 2 ;;
        --execute)  DRY_RUN=false; shift ;;
        --before)   BEFORE="$2"; shift 2 ;;
        --keep)     KEEP="$2"; shift 2 ;;
        --module)   MODULE="$2"; shift 2 ;;
        --report)   REPORT_ONLY=true; shift ;;
        -h|--help)  usage ;;
        *) echo "알 수 없는 옵션: $1"; usage ;;
    esac
done

# 대상 디렉토리 확인 (필수)
if [ -z "$TARGET_DIR" ]; then
    echo -e "${RED}오류: --dir 옵션은 필수입니다.${NC}"
    usage
fi
if [ ! -d "$TARGET_DIR" ]; then
    echo -e "${RED}오류: 대상 디렉토리가 없습니다: $TARGET_DIR${NC}"
    exit 1
fi

# 백업 파일 목록 수집
find_backups() {
    local path="$TARGET_DIR"
    if [ -n "$MODULE" ]; then
        # mhcweb 또는 mhcapp 하위에서 모듈 디렉토리 자동탐지
        local found=""
        for area in mhcweb mhcapp; do
            local candidate="$TARGET_DIR/kr/go/mhc/$area/$MODULE"
            if [ -d "$candidate" ]; then
                found="$candidate"
                break
            fi
        done
        if [ -z "$found" ]; then
            echo -e "${RED}오류: 모듈 디렉토리를 찾을 수 없습니다: kr/go/mhc/{mhcweb,mhcapp}/$MODULE${NC}"
            exit 1
        fi
        path="$found"
    fi
    {
        find "$path" -name "*.class_*" -type f
        find "$path" -name "*_[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9].class" -type f
        find "$path" -name "*.class[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]" -type f
        find "$path" -type f | grep -E '_[0-9]{8}$' | grep -v '\.class' || true
    } | sort -u
}

# 날짜 추출 (파일명에서 YYYYMMDD 부분)
extract_date() {
    echo "$1" | grep -oE '[0-9]{8}' | tail -1
}

# === 현황 보고 ===
report() {
    echo -e "${BLUE}=== 백업 파일 현황 ===${NC}"
    echo ""

    local all_files
    all_files=$(find "$TARGET_DIR" -type f ! -name ".DS_Store" | wc -l | tr -d ' ')
    echo -e "대상 디렉토리: ${YELLOW}${TARGET_DIR}${NC}"
    echo -e "전체 파일: ${YELLOW}${all_files}개${NC}"

    local total
    total=$(find_backups | wc -l | tr -d ' ')
    echo -e "백업 파일: ${YELLOW}${total}개${NC}"
    echo ""

    # 모듈별 분포
    echo -e "${BLUE}[모듈별 분포]${NC}"
    find_backups | sed -n 's|.*mhcweb/\([^/]*\)/.*|\1|p' | sort | uniq -c | sort -rn | awk '{printf "  %-12s %s개\n", $2, $1}'
    echo ""

    # 레이어별 분포
    echo -e "${BLUE}[레이어별 분포]${NC}"
    find_backups | while read -r f; do
        case "$f" in
            */service/impl/*) echo "service/impl" ;;
            */controller/*)   echo "controller" ;;
            */service/*)      echo "service" ;;
            *)                echo "other" ;;
        esac
    done | sort | uniq -c | sort -rn | awk '{printf "  %-15s %s개\n", $2, $1}'
}

# === 삭제 대상 계산 ===
compute_targets() {
    # 임시 파일 사용
    local tmp_all=$(mktemp)
    local tmp_delete=$(mktemp)
    local tmp_keep=$(mktemp)
    local tmp_orig=$(mktemp)
    trap "rm -f '$tmp_all' '$tmp_delete' '$tmp_keep' '$tmp_orig'" EXIT

    find_backups > "$tmp_all"

    # 원본 파일명 목록 (중복 제거)
    # 패턴1: Foo.class_20251124 -> Foo.class
    # 패턴2: Foo_20210209.class -> Foo.class
    # 패턴3: Foo.class20200629 -> Foo.class
    # 패턴4: Foo_20220616 -> Foo.class (확장자 없음)
    sed -e 's/\.class_.*/\.class/' -e 's/_[0-9]\{8\}\.class$/.class/' -e 's/\.class[0-9]\{8\}$/.class/' -e 's/_[0-9]\{8\}$/.class/' "$tmp_all" | sort -u > "$tmp_orig"

    while IFS= read -r original; do
        [ -z "$original" ] && continue

        # 해당 원본의 백업 파일들 (모든 패턴 매칭)
        local base="${original%.class}"
        local backups
        backups=$({ grep -F "${base}.class" "$tmp_all" || true; grep -F "${base}_" "$tmp_all" || true; } | sort -u | grep -v "^${original}$" || true)
        [ -z "$backups" ] && continue

        # --before 필터 적용
        if [ -n "$BEFORE" ]; then
            backups=$(echo "$backups" | while read -r f; do
                [ -z "$f" ] && continue
                local d
                d=$(extract_date "$f")
                if [ -n "$d" ] && [ "$d" \< "$BEFORE" ]; then
                    echo "$f"
                fi
            done)
        fi

        [ -z "$backups" ] && continue

        # 총 개수
        local total_count
        total_count=$(echo "$backups" | grep -c . || true)

        if [ "$total_count" -le "$KEEP" ]; then
            echo "$backups" >> "$tmp_keep"
        else
            local skip=$((total_count - KEEP))
            local i=0
            echo "$backups" | while read -r f; do
                [ -z "$f" ] && continue
                i=$((i + 1))
                if [ "$i" -le "$skip" ]; then
                    echo "$f" >> "$tmp_delete"
                else
                    echo "$f" >> "$tmp_keep"
                fi
            done
        fi
    done < "$tmp_orig"

    local delete_count=$(wc -l < "$tmp_delete" 2>/dev/null | tr -d ' ')
    local keep_count=$(wc -l < "$tmp_keep" 2>/dev/null | tr -d ' ')
    [ -z "$delete_count" ] && delete_count=0
    [ -z "$keep_count" ] && keep_count=0

    # 결과 출력
    echo -e "${BLUE}=== 정리 계획 ===${NC}"
    echo ""
    echo -e "삭제 대상: ${RED}${delete_count}개${NC}"
    echo -e "보존 대상: ${GREEN}${keep_count}개${NC}"

    if [ -n "$BEFORE" ]; then
        echo -e "날짜 필터: ${BEFORE} 이전"
    fi
    echo -e "보존 정책: 파일당 최신 ${KEEP}개"
    echo ""

    if [ "$delete_count" -eq 0 ]; then
        echo -e "${GREEN}삭제할 파일이 없습니다.${NC}"
        return
    fi

    echo -e "${RED}[삭제 대상]${NC}"
    while IFS= read -r f; do
        [ -z "$f" ] && continue
        echo "  ${f#$BASE_DIR/}"
    done < "$tmp_delete"
    echo ""

    if [ "$DRY_RUN" = true ]; then
        echo -e "${YELLOW}[DRY-RUN] 실제 삭제하려면 --execute 옵션을 추가하세요.${NC}"
    else
        echo -e "${RED}삭제를 실행합니다...${NC}"
        local deleted=0
        while IFS= read -r f; do
            [ -z "$f" ] && continue
            rm -f "$f"
            deleted=$((deleted + 1))
        done < "$tmp_delete"
        echo -e "${GREEN}완료: ${deleted}개 파일 삭제됨${NC}"

        # 검증
        local remaining
        remaining=$(find_backups | wc -l | tr -d ' ')
        echo -e "남은 백업 파일: ${remaining}개"
    fi
}

# === 메인 ===
echo -e "${BLUE}==============================${NC}"
echo -e "${BLUE} 백업 파일 정리 도구${NC}"
echo -e "${BLUE}==============================${NC}"
echo ""

if [ -n "$MODULE" ]; then
    echo -e "모듈 필터: ${YELLOW}${MODULE}${NC}"
fi

report

if [ "$REPORT_ONLY" = true ]; then
    exit 0
fi

echo ""
compute_targets
