# Java 1.7 컴파일 에이전트

## 역할

지정된 프로젝트를 JDK 1.7 + Maven 3.2.5 환경으로 컴파일한다. SVN 소스를 운영 환경과 동일한 컴파일러로 빌드하여 .class 파일 비교 시 컴파일러 차이로 인한 false positive를 제거한다.

## 대상 프로젝트

- `mhcweb` — 웹 포털
- `smhcweb` — 웹 포털 (시군구)
- `mhcapp` — 모바일 앱 백엔드
- `smhcapp` — 모바일 앱 백엔드 (시군구)

## 환경 설정

```
JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.7.0_80.jdk/Contents/Home
MAVEN_OPTS="-Dhttps.protocols=TLSv1.2"
MVN=~/tools/apache-maven-3.2.5/bin/mvn
```

## 컴파일 절차

### 1. 사전 조건 체크

컴파일 전에 반드시 아래 항목을 확인한다:

```bash
# JDK 1.7 존재 확인
ls /Library/Java/JavaVirtualMachines/jdk1.7.0_80.jdk/Contents/Home/bin/javac

# Maven 3.2.5 존재 확인
ls ~/tools/apache-maven-3.2.5/bin/mvn

# 프로젝트 pom.xml 존재 확인
ls {project}/pom.xml

# 로컬 의존성 설치 확인 (ojdbc7)
ls ~/.m2/repository/com/oracle/ojdbc7/12.1.0.2/ojdbc7-12.1.0.2.jar

# 로컬 의존성 설치 확인 (crosscert) — mhcweb, smhcweb만 해당
ls ~/.m2/repository/crosscert/crosscert/2.2/crosscert-2.2.jar
```

누락된 사전 조건이 있으면 `BUILD.md`를 참조하여 설치를 안내한다.

### 2. 컴파일 실행

```bash
cd {project}
JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.7.0_80.jdk/Contents/Home \
MAVEN_OPTS="-Dhttps.protocols=TLSv1.2" \
~/tools/apache-maven-3.2.5/bin/mvn compile
```

### 3. 컴파일 결과 확인

- 출력 경로: `{project}/target/classes/`
- 타겟 버전: Java 1.7 (class major version 51)

### 4. mtime 복사

컴파일된 .class 파일의 mtime은 컴파일 시점이다. .java 파일의 커밋 시간을 .class에 복사한다:

```bash
python scripts/copy_mtime.py {project}
```

- 소스: `{project}/src/main/java/`
- 대상: `{project}/target/classes/`
- 내부 클래스(`Foo$Bar.class` 등)는 해당 `.java` 파일의 mtime을 따른다

### 5. svn 디렉토리에 배포

컴파일된 클래스 파일을 비교용 svn 디렉토리로 복사한다:

```bash
mkdir -p {project}/svn
rm -rf {project}/svn/kr
cp -R {project}/target/classes/kr {project}/svn/kr
rm -rf {project}/target
```

- `{project}/svn/kr/` — prd/kr/과 비교하는 대상 디렉토리

## 오류 처리

### SSL/TLS 오류

`PKIX path building failed` 또는 `SSLHandshakeException` 발생 시:
- `MAVEN_OPTS`에 `-Dhttps.protocols=TLSv1.2`가 설정되었는지 확인
- JDK 1.7의 cacerts가 업데이트되었는지 확인 (`BUILD.md` > JDK 1.7 SSL 인증서 업데이트 참조)

### 의존성 누락

`Could not resolve dependencies` 발생 시:
- ojdbc7: `BUILD.md` > Oracle JDBC 드라이버 설치 절차 참조
- crosscert: `BUILD.md` > crosscert 설치 절차 참조. 프로젝트의 `src/main/webapp/WEB-INF/lib/crosscert_2.2.jar`를 로컬 저장소에 설치

### egovframe.go.kr 저장소 불안정

`Could not transfer artifact from/to egovframe` 발생 시:
- `~/.m2/repository/` 내 손상된 캐시 파일(`.lastUpdated` 파일) 삭제 후 재시도
- 반복 실패 시 네트워크 상태 확인 후 시간을 두고 재시도

## 완료 후 작업

1. **결과 보고** — BUILD SUCCESS/FAILURE 여부와 주요 메시지 요약
2. **BUILD.md 업데이트 제안** — 새로운 의존성이나 설정 변경이 필요했다면 BUILD.md 업데이트 제안
3. **출력 경로 안내** — 컴파일된 클래스 파일 위치: `{project}/svn/kr/`
4. **svn 배포 확인** — `{project}/svn/kr/` 디렉토리에 클래스 파일이 복사되었는지 확인

## 참조 파일

- `BUILD.md` — 빌드 환경 상세 설정
- `{project}/pom.xml` — Maven 프로젝트 설정
