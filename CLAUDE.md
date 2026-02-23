# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

보건소 채움건강 Web WAS1 서버 — a Korean government health center web portal built on the eGov Framework (전자정부 표준프레임워크) running on JEUS (TmaxSoft) application server. The repository contains **compiled .class files only** (no Java source files). Source code is managed separately.

## Repository Structure

This repo holds deployment artifacts, not source code:

- `classes/` — Primary compiled Java classes (`kr/go/mhc/`)
- `WAS/jeus/mhcweb/WEB-INF/classes/` — JEUS deployment copy of the same classes
- Timestamped backup files (e.g., `*.class_20251124`) are manual version snapshots

## Architecture

### Package Layout (`kr.go.mhc`)

```
kr.go.mhc.common          # Shared layer (controllers, services, utils, crontab)
kr.go.mhc.mhcweb.*        # Main web application (9 feature modules)
kr.go.mhc.mhcapp.*        # Mobile app backend (3 modules)
kr.go.mhc.service          # Global service interfaces + impls
```

### Feature Modules (mhcweb)

| Module | Purpose |
|--------|---------|
| `cm` | 공통관리 — notices, health exams, push, edu videos, nutrition/exercise codes |
| `pm` | 성과관리 — app usage stats, device rates, target registration/processing |
| `sm` | 표본관리 |
| `ms` | 모바일서비스 |
| `gn` | 일반/네비게이션 |
| `mr` | 경영보고 |
| `st` | 통계 |

### Layered Architecture

```
Controller → Service (interface) → ServiceImpl → DAO (EgovAbstractMapper) → DB
```

Each module follows this Spring MVC + eGov pattern consistently. Controllers handle HTTP requests, services contain business logic, DAOs use MyBatis-based `EgovAbstractMapper`.

### Common Layer (`kr.go.mhc.common`)

- **Controllers**: `LoginController`, `CommonController`, `ErrorController`, `GridController`
- **Interceptors**: `LoginCheckInterceptor` for auth
- **Crontab**: `Scheduler`, `PushBatchSender` for scheduled tasks
- **Utilities**: `FileUtil`, `DateUtil`, `StringUtil`, `PaginationUtil`, `CookieUtil`, `PushMessageUtil`, etc.
- **Filters**: `CORSFilter`
- **Integrations**: `FirebaseInitializer` for FCM push notifications

### Dual Platform

- **mhcweb**: Web portal (9 modules, JSP views)
- **mhcapp**: Mobile app API backend (3 modules: `sv`, `mr`, `cm`)

## Deployment

- **WAS**: JEUS (TmaxSoft) — Korean enterprise application server
- **Deploy path**: `WAS/jeus/mhcweb/WEB-INF/classes/`
- **No build scripts in this repo** — classes are compiled externally and deployed here
- Configuration (web.xml, Spring context, application.properties) is managed outside this repository

## Key Conventions

- Package naming follows Korean government domain: `kr.go.mhc`
- eGov Framework base classes: `EgovAbstractMapper`, `EgovComExcepHndlr`
- Module structure is consistent: each module has `controller/`, `service/`, `service/impl/` subdirectories
- Backup versioning uses filename suffix pattern: `ClassName.class_YYYYMMDD`
