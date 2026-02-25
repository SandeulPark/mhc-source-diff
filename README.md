# 보건소 소스 비교 (PRD vs SVN)


- SVN 소스
  - SVN Checkout
    - 커밋 일시 가져오기 
      - smhcweb: svn checkout --config-option config:miscellany:use-commit-times=yes svn://127.0.0.1:13690/smhcweb/src/main/java "smhcweb/svn_java"
      - mhcweb: svn checkout --config-option config:miscellany:use-commit-times=yes svn://127.0.0.1:13690/mhcweb/src/main/java "mhcweb/svn_java"
      - smhcapp: svn checkout --config-option config:miscellany:use-commit-times=yes svn://127.0.0.1:13690/smhcapp/src/main/java "smhcapp/svn_java"
      - mhcapp: svn checkout --config-option config:miscellany:use-commit-times=yes svn://127.0.0.1:13690/mhcapp/src/main/java "mhcapp/svn_java"
  - 컴파일 (cluade 요청. AGENTS에 정의했음.)
    - Java 1.7 설치
    - Maven 3.2.5 설치
    - pom.xml만 있으면 된다.
  - 컴파일하면 target/classes에 class 파일들이 생성된다. mtime이 컴파일 된 시점이기 때문에 .java 파일에서 복사해와야 한다. `claude 요청`

- PRD 소스
  - 원격 서버 파일 가져오기
    - 압축: 
      - smhcweb: zip -r $HOME/sources_zip/smhc_web_sources_$(date +%Y%m%d).zip /WAS/jeus/smhcweb/WEB-INF/classes/kr
      - mhcweb: zip -r $HOME/sources_zip/mhc_web_sources_$(date +%Y%m%d).zip /WAS/jeus/mhcweb/WEB-INF/classes/kr
      - smhcapp: zip -r $HOME/sources_zip/smhc_app_sources_$(date +%Y%m%d).zip /WAS/jeus/smhcapp/WEB-INF/classes/kr
      - mhcapp: zip -r $HOME/sources_zip/mhc_app_sources_$(date +%Y%m%d).zip /WAS/jeus/mhcapp/WEB-INF/classes/kr
    - 이동: 
      - smhcweb: scp -P 13522 jeus@localhost:/home/jeus/sources_zip/smhc-web-sources .
      - mhcweb: scp -P 13522 jeus@localhost:/home/jeus/sources_zip/mhc-web-sources .
      - smhcapp: scp -P 13522 jeus@localhost:/home/jeus/sources_zip/smhc-app-sources .
      - mhcapp: scp -P 13522 jeus@localhost:/home/jeus/sources_zip/mhc-app-sources .
  - scripts/clean-backups.sh 실행해서 백업 파일 제거. (파일에 실행 방법 있음.) (에이전트)

- 공통
  - 파일의 최종수정일시는 github에 올리면 사라지기 때문에 scripts/save_mtime.py로 파일로 만들어둔다.
    - prd: python3 scripts/save_mtime.py smhcweb prd
    - svn: python3 scripts/save_mtime.py smhcweb svn
    - 한 번에 실행: python3 scripts/save_mtime.py smhcweb prd; python3 scripts/save_mtime.py smhcweb svn
  - scripts/gen_excel.py로 prd, svn .class를 비교 정리한 엑셀 파일 생성. 
    - smhcweb: `uv run --with openpyxl python3 scripts/gen_excel.py smhcweb` 
    - mhcweb: `uv run --with openpyxl python3 scripts/gen_excel.py mhcweb`
    - smhcapp: `uv run --with openpyxl python3 scripts/gen_excel.py smhcapp`
    - mhcapp: `uv run --with openpyxl python3 scripts/gen_excel.py mhcapp`



- 이슈
  - 1.6으로 컴파일 된 파일이 있다. 일부 파일 중에 java 6으로 컴파일 된 파일이 있다. 
  