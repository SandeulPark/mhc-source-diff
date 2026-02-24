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
mhcweb, smhcweb 각각의 디렉토리에서 실행해야 합니다.

```bash
# mhcweb
cd mhcweb
JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.7.0_80.jdk/Contents/Home \
MAVEN_OPTS="-Dhttps.protocols=TLSv1.2" \
~/tools/apache-maven-3.2.5/bin/mvn install:install-file \
  -DgroupId=crosscert \
  -DartifactId=crosscert \
  -Dversion=2.2 \
  -Dpackaging=jar \
  -Dfile=src/main/webapp/WEB-INF/lib/crosscert_2.2.jar

# smhcweb
cd ../smhcweb
JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.7.0_80.jdk/Contents/Home \
MAVEN_OPTS="-Dhttps.protocols=TLSv1.2" \
~/tools/apache-maven-3.2.5/bin/mvn install:install-file \
  -DgroupId=crosscert \
  -DartifactId=crosscert \
  -Dversion=2.2 \
  -Dpackaging=jar \
  -Dfile=src/main/webapp/WEB-INF/lib/crosscert_2.2.jar
```

> **참고**: smhcweb의 `pom.xml`에는 원래 crosscert 의존성이 누락되어 있어 수동으로 추가해야 합니다. 아래 내용을 `<dependencies>` 안에 추가하세요:
> ```xml
> <dependency>
>     <groupId>crosscert</groupId>
>     <artifactId>crosscert</artifactId>
>     <version>2.2</version>
> </dependency>
> ```

#### mhcapp, smhcapp 상용 라이브러리

mhcapp, smhcapp은 보안/인증 관련 상용 라이브러리를 사용합니다. 각 프로젝트의 `src/main/webapp/WEB-INF/lib/`에 있는 JAR 파일을 로컬 저장소에 설치합니다.

```bash
cd mhcapp

JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.7.0_80.jdk/Contents/Home \
MAVEN_OPTS="-Dhttps.protocols=TLSv1.2" \
MVN=~/tools/apache-maven-3.2.5/bin/mvn

LIB=src/main/webapp/WEB-INF/lib

# DreamSecurity DSToolkit (암호화)
$MVN install:install-file -DgroupId=com.dreamsecurity -DartifactId=dstoolkit -Dversion=3.4.2.0 -Dpackaging=jar -Dfile=$LIB/DSToolkit-v3.4.2.0.jar

# DreamSecurity MagicKeyPad (보안 키패드)
$MVN install:install-file -DgroupId=com.dreamsecurity -DartifactId=magickeypad -Dversion=1.0.1.0 -Dpackaging=jar -Dfile=$LIB/MagicKeypadSever_1.0.1.0.jar

# Extrus eXafe (E2E 암호화)
$MVN install:install-file -DgroupId=com.extrus -DartifactId=exafe-common -Dversion=1.0.1 -Dpackaging=jar -Dfile=$LIB/exafe-common-1.0.1.jar
$MVN install:install-file -DgroupId=com.extrus -DartifactId=exafe-e2e -Dversion=1.0.0 -Dpackaging=jar -Dfile=$LIB/exafe-e2e-server1.0.0.jar

# Initech INICrypto (전자서명)
$MVN install:install-file -DgroupId=com.initech -DartifactId=INICrypto -Dversion=4.1.7 -Dpackaging=jar -Dfile=$LIB/INICrypto_v4.1.7.jar

# NICE 본인인증
$MVN install:install-file -DgroupId=NiceID -DartifactId=NiceID -Dversion=1.0 -Dpackaging=jar -Dfile=$LIB/NiceID.jar
```

> **참고**: mhcapp의 `pom.xml`에는 원래 이 의존성들이 주석 처리되어 있거나 누락되어 있어 수동으로 추가해야 합니다. metadata-extractor와 json-simple은 Maven Central에서 자동 다운로드됩니다.

> **참고**: smhcapp도 동일한 상용 라이브러리를 사용합니다. smhcapp의 `pom.xml`에는 `${basedir}` 기반 system scope로 설정되어 있으므로 별도 로컬 저장소 설치 없이 컴파일 가능합니다. 단, ojdbc7은 원래 Windows systemPath(`C:/repositoryEgo/...`)로 되어 있어 로컬 Maven 저장소(12.1.0.2)를 사용하도록 수정해야 합니다.

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

### mhcapp

상용 라이브러리 사전 설치 필요 (위 [mhcapp, smhcapp 상용 라이브러리](#mhcapp-smhcapp-상용-라이브러리) 참조).

```bash
cd mhcapp

JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.7.0_80.jdk/Contents/Home \
MAVEN_OPTS="-Dhttps.protocols=TLSv1.2" \
~/tools/apache-maven-3.2.5/bin/mvn compile
```

- **출력 경로**: `mhcapp/target/classes/`
- **타겟 버전**: Java 1.7 (class major version 51)

### smhcapp

상용 라이브러리는 `pom.xml`에 system scope로 설정되어 별도 설치 불필요. ojdbc7은 로컬 Maven 저장소 사용 (위 [Oracle JDBC 드라이버](#oracle-jdbc-드라이버-ojdbc7) 참조).

```bash
cd smhcapp

JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.7.0_80.jdk/Contents/Home \
MAVEN_OPTS="-Dhttps.protocols=TLSv1.2" \
~/tools/apache-maven-3.2.5/bin/mvn compile
```

- **출력 경로**: `smhcapp/target/classes/`
- **타겟 버전**: Java 1.7 (class major version 51)

## MAVEN_OPTS 설명

`-Dhttps.protocols=TLSv1.2` : JDK 1.7은 기본 TLS 1.0을 사용하지만, Maven 저장소가 TLS 1.2를 요구하므로 명시적으로 활성화해야 합니다.

## 참고사항

- `egovframe.go.kr` Maven 저장소가 불안정할 경우, 로컬 `~/.m2/repository`에 캐시된 손상 파일을 삭제 후 재시도하세요.