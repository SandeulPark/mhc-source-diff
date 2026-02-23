# 보건소 채움건강 web WAS1 서버 소스


- SVN Checkout
- 컴파일
  - Java 1.7 설치
  - Maven 3.2.5 설치
- 컴파일된 파일 비교하기

- 원격 서버 파일 가져오기
  - scp -P 13522 jeus@localhost:/home/jeus/mhc-web-sources_20260223.zip .
- 커밋 일시 가져오기 svn checkout --config-option config:miscellany:use-commit-times=yes