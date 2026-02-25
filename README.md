# 보건소 채움건강 소스 비교 (PRD vs SVN)

보건소 채움건강 4개 프로젝트의 **운영(prd) 서버 클래스 파일**과 **SVN 클래스 파일**을 비교하여 정합성을 분석하는 저장소.

## 대상 프로젝트

| 디렉터리 | 프로젝트 | 설명 |
|----------|----------|------|
| `mhcweb/` | 채움건강 웹 | 웹 포털 (488건) |
| `mhcapp/` | 채움건강 앱 | 모바일 앱 백엔드 (145건) |
| `smhcweb/` | 오늘건강 웹 | 스마트 웹 포털 (187건) |
| `smhcapp/` | 오늘건강 앱 | 스마트 앱 백엔드 (91건) |

각 프로젝트 디렉터리 내부 구조:

```
{project}/
├── prd/              # 운영 서버 클래스 파일
├── svn/              # SVN 클래스 파일
├── svn_java/         # SVN Java 소스 (있는 경우)
├── prd_mtime.json    # prd 파일 수정시간 기록
├── svn_mtime.json    # svn 파일 수정시간 기록
└── classes_comparison.xlsx  # 비교 결과 엑셀
```

## 산출물

| 파일 | 설명 |
|------|------|
| `{project}/classes_comparison.xlsx` | 프로젝트별 파일 단위 상세 비교 (상태, 크기, JDK 버전, 최신 버전 위치) |
| `현황_260225.md` | 4개 프로젝트 전체 비교 현황 요약 |

## 사용법

### 비교 엑셀 생성

```bash
uv run --with openpyxl python3 scripts/gen_excel.py {프로젝트 디렉터리}
```

예시:
```bash
uv run --with openpyxl python3 scripts/gen_excel.py mhcweb
```

### 기타 스크립트

| 스크립트 | 용도 |
|---------|------|
| `scripts/gen_excel.py` | prd/svn 클래스 비교 후 엑셀 생성 |
| `scripts/save_mtime.py` | 파일 수정시간 JSON 저장 |
| `scripts/copy_mtime.py` | 저장된 수정시간 복원 |
| `scripts/clean-backups.sh` | 백업 파일(`*.class_YYYYMMDD`) 정리 |

## 비교 결과 요약 (2026-02-25)

| 비교상태 | mhcweb | mhcapp | smhcweb | smhcapp | 합계 |
|----------|-------:|-------:|--------:|--------:|-----:|
| 변경없음 | 320 | 76 | 104 | 44 | **544** |
| 코드변경 | 121 | 69 | 83 | 45 | **318** |
| SVN에만 존재 | 39 | 0 | 0 | 0 | **39** |
| prd에만 존재 | 8 | 0 | 0 | 2 | **10** |
| **합계** | **488** | **145** | **187** | **91** | **911** |

전체 911건 중 59.7%가 변경 없음, 34.9%가 코드 변경. 상세 분석은 [`현황_260225.md`](현황_260225.md) 참조.

## 커밋 이력을 통한 비교 시점 추적

모듈 비교가 완료될 때마다 해당 프로젝트 단위로 커밋을 남긴다. 커밋 메시지에 `[비교]` 태그를 사용하여 언제 어떤 프로젝트의 소스를 비교했는지 git log로 추적할 수 있다.

```
[비교] mhcweb
[비교] mhcapp
[비교] smhcweb
[비교] smhcapp
```

```bash
# 비교 이력 확인
git log --oneline --grep="비교"
```

## 관련 문서

- [`CLAUDE.md`](CLAUDE.md) — Claude Code 작업 가이드
- [`AGENTS/`](AGENTS/) — 에이전트 정의 (컴파일, 백업 정리 등)
- [`workflow.md`](workflow.md) — 작업 흐름
