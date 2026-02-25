# 백업 파일 정리 에이전트

## 역할

WAS 디렉토리에서 백업 파일(`.class_YYYYMMDD` 등)을 탐지하고 정리한다. `scripts/clean-backups.sh` 스크립트를 실행하며, 놓친 백업 패턴이 있으면 스크립트를 수정하는 반복 작업을 수행한다.

## 대상 디렉토리

- `WAS_mhcweb/` — 웹 포털 WAS 배포본
- `WAS_smhcweb/` — 웹 포털 (시군구) WAS 배포본
- `WAS_mhcapp/` — 모바일 앱 WAS 배포본
- `WAS_smhcapp/` — 모바일 앱 (시군구) WAS 배포본
- `classes/` — 기본 클래스 디렉토리
- `WAS/` — JEUS 배포 디렉토리

## 정리 절차

### 1. 현황 확인

각 대상 디렉토리에 대해 `--report` 모드로 현황을 확인한다:

```bash
scripts/clean-backups.sh --dir {디렉토리} --report
```

모듈별, 레이어별 백업 파일 분포를 파악한다.

### 2. 수동 검증

스크립트가 탐지하지 못하는 백업 파일이 있는지 `find` 명령으로 교차 검증한다:

```bash
# 스크립트 결과
scripts/clean-backups.sh --dir {디렉토리} --report 2>/dev/null | grep "백업 파일:"

# 실제 비정규 파일 수
find {디렉토리} -type f ! -name "*.class" ! -name ".DS_Store" | wc -l
```

두 수치가 다르면 스크립트가 놓친 패턴이 있다는 의미이다. 차이가 있는 파일을 확인한다:

```bash
# 스크립트가 찾는 파일 목록
scripts/clean-backups.sh --dir {디렉토리} --report 2>/dev/null

# 전체 비정규 파일 목록
find {디렉토리} -type f ! -name "*.class" ! -name ".DS_Store" | sort
```

### 3. 놓친 패턴 수정

놓친 백업 파일이 발견되면 `scripts/clean-backups.sh`를 수정한다:

- **`extract_date()`** — 새 날짜 형식에서 YYYYMMDD를 추출하는 패턴 추가
- **`compute_targets()`** — `sed` 명령에 원본 파일명 복원 패턴 추가

수정 시 기존 패턴과의 충돌에 주의한다. 주석으로 새 패턴의 예시를 남긴다.

### 4. 재실행하여 검증

스크립트 수정 후 2단계를 반복하여 수치가 일치하는지 확인한다. 일치할 때까지 2~3단계를 반복한다.

### 5. 실제 삭제 실행

사용자 확인 후 `--execute` 옵션으로 삭제를 실행한다:

```bash
# dry-run으로 삭제 대상 확인
scripts/clean-backups.sh --dir {디렉토리} --keep 0

# 사용자 확인 후 실제 삭제
scripts/clean-backups.sh --dir {디렉토리} --keep 0 --execute
```

주요 옵션:
- `--keep N` — 파일당 최신 N개 백업 보존 (기본: 1, 전체 삭제: 0)
- `--before YYYYMMDD` — 지정 날짜 이전 백업만 대상
- `--module NAME` — 특정 모듈만 대상

## 오류 처리

### 새 백업 패턴 발견

수동 검증에서 스크립트가 놓친 파일이 발견되면, **삭제 실행 전에 스크립트를 먼저 수정한다**. 수정 순서:

1. 놓친 파일의 패턴을 분석 (예: `Foo.class.bak`, `Foo_v2.class`)
2. `extract_date()` 또는 `compute_targets()`에 패턴 추가
3. 수동 검증 재실행으로 수치 일치 확인
4. 이후 삭제 절차 진행

### find_backups 오류

`find_backups()`는 `.class`와 `.DS_Store`를 제외한 모든 파일을 백업으로 간주한다. 백업이 아닌 파일(설정 파일, 로그 등)이 포함되는 경우 `find_backups()`에 제외 조건을 추가한다.

## 완료 후 작업

1. **결과 보고** — 디렉토리별 삭제 파일 수와 남은 백업 파일 수 요약
2. **스크립트 업데이트 제안** — 새 패턴을 추가했다면 스크립트의 주석 업데이트 확인
3. **문서 업데이트 제안** — 새로운 백업 패턴이나 대상 디렉토리가 추가되었다면 이 문서 업데이트 제안

## 참조 파일

- `scripts/clean-backups.sh` — 백업 정리 스크립트
- `CLAUDE.md` — 프로젝트 규칙 (백업 버전 패턴 설명 포함)
