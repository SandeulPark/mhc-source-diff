# 빌드 가이드

## 빌드 환경 요구사항

- **JDK**: 1.7.0_80 (Oracle Java SE 7)
- **Maven**: 3.2.5 (JDK 1.7을 지원하는 마지막 버전)

## 빌드 환경 설정

### 1. Maven 3.2.5 설치

Maven 3.2.5를 다운로드하여 `~/tools/`에 설치합니다.

```bash
# 다운로드
curl -L -o /tmp/apache-maven-3.2.5-bin.tar.gz \
  https://archive.apache.org/dist/maven/maven-3/3.2.5/binaries/apache-maven-3.2.5-bin.tar.gz

# 압축 해제
mkdir -p ~/tools
tar -xzf /tmp/apache-maven-3.2.5-bin.tar.gz -C ~/tools/

# 설치 확인
JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.7.0_80.jdk/Contents/Home \
~/tools/apache-maven-3.2.5/bin/mvn -version
```

### 2. JDK 1.7 SSL 인증서 업데이트

JDK 1.7의 CA 인증서가 오래되어 Maven Central 등 HTTPS 저장소 접속이 실패합니다.
JDK 17 (또는 그 이상)의 최신 `cacerts` 파일을 JDK 1.7에 복사합니다.

```bash
# 기존 cacerts 백업
sudo cp \
  /Library/Java/JavaVirtualMachines/jdk1.7.0_80.jdk/Contents/Home/jre/lib/security/cacerts \
  /Library/Java/JavaVirtualMachines/jdk1.7.0_80.jdk/Contents/Home/jre/lib/security/cacerts.bak

# JDK 17의 cacerts를 JDK 1.7에 복사
sudo cp \
  ~/Library/Java/JavaVirtualMachines/corretto-17.0.17/Contents/Home/lib/security/cacerts \
  /Library/Java/JavaVirtualMachines/jdk1.7.0_80.jdk/Contents/Home/jre/lib/security/cacerts
```

> **참고**: JDK 17 경로는 설치 환경에 따라 다를 수 있습니다. `cacerts` 파일만 복사하면 되므로 JDK 8 이상 어떤 버전이든 사용 가능합니다.

### 3. Maven 저장소에 없는 의존성 설치

아래 라이브러리들은 Maven Central에 없으므로 로컬 저장소에 수동 설치해야 합니다.

#### Oracle JDBC 드라이버 (ojdbc7)

```bash
# JAR 다운로드
curl -L -o /tmp/ojdbc7-12.1.0.2.jar \
  "https://github.com/MHTaleb/ojdbc7/raw/master/ojdbc7.jar"

# 로컬 Maven 저장소에 설치
JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.7.0_80.jdk/Contents/Home \
MAVEN_OPTS="-Dhttps.protocols=TLSv1.2" \
~/tools/apache-maven-3.2.5/bin/mvn install:install-file \
  -DgroupId=com.oracle \
  -DartifactId=ojdbc7 \
  -Dversion=12.1.0.2 \
  -Dpackaging=jar \
  -Dfile=/tmp/ojdbc7-12.1.0.2.jar
```

#### crosscert 공인인증서 라이브러리

각 프로젝트의 `src/main/webapp/WEB-INF/lib/crosscert_2.2.jar`를 사용합니다.

```bash
JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.7.0_80.jdk/Contents/Home \
MAVEN_OPTS="-Dhttps.protocols=TLSv1.2" \
~/tools/apache-maven-3.2.5/bin/mvn install:install-file \
  -DgroupId=crosscert \
  -DartifactId=crosscert \
  -Dversion=2.2 \
  -Dpackaging=jar \
  -Dfile=src/main/webapp/WEB-INF/lib/crosscert_2.2.jar
```

## 컴파일

### 공통 컴파일 명령

모든 프로젝트는 동일한 JDK/Maven 환경에서 컴파일합니다.

```bash
JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.7.0_80.jdk/Contents/Home \
MAVEN_OPTS="-Dhttps.protocols=TLSv1.2" \
~/tools/apache-maven-3.2.5/bin/mvn compile
```

### mhcweb

```bash
cd mhcweb

JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.7.0_80.jdk/Contents/Home \
MAVEN_OPTS="-Dhttps.protocols=TLSv1.2" \
~/tools/apache-maven-3.2.5/bin/mvn compile
```

- **출력 경로**: `mhcweb/target/classes/`
- **타겟 버전**: Java 1.7 (class major version 51)

### smhcweb

```bash
cd smhcweb

JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.7.0_80.jdk/Contents/Home \
MAVEN_OPTS="-Dhttps.protocols=TLSv1.2" \
~/tools/apache-maven-3.2.5/bin/mvn compile
```

- **출력 경로**: `smhcweb/target/classes/`
- **타겟 버전**: Java 1.7 (class major version 51)

## MAVEN_OPTS 설명

`-Dhttps.protocols=TLSv1.2` : JDK 1.7은 기본 TLS 1.0을 사용하지만, Maven 저장소가 TLS 1.2를 요구하므로 명시적으로 활성화해야 합니다.

## 참고사항

- `egovframe.go.kr` Maven 저장소가 불안정할 경우, 로컬 `~/.m2/repository`에 캐시된 손상 파일을 삭제 후 재시도하세요.