"""
.java 파일의 mtime을 대응하는 .class 파일에 복사한다.

컴파일하면 .class의 mtime이 컴파일 시점으로 설정되므로,
SVN checkout된 .java의 커밋 시간을 .class에 복사하여 정확한 비교를 가능하게 한다.

사용법: python scripts/copy_mtime.py {project} [target_subdir]
  target_subdir: 타겟 서브디렉토리 (기본값: target/classes)
  예) python scripts/copy_mtime.py mhcapp svn
"""

import os
import sys
from pathlib import Path


def copy_mtime(project_dir: str, target_subdir: str = "target/classes") -> None:
    project = Path(project_dir)
    src_root = project / "src" / "main" / "java"
    target_root = project / target_subdir

    if not src_root.is_dir():
        print(f"소스 디렉토리 없음: {src_root}")
        sys.exit(1)
    if not target_root.is_dir():
        print(f"타겟 디렉토리 없음: {target_root}")
        sys.exit(1)

    copied = 0
    no_match = []

    for java_file in sorted(src_root.rglob("*.java")):
        rel = java_file.relative_to(src_root)
        class_dir = target_root / rel.parent
        stem = java_file.stem  # e.g. "Foo"

        if not class_dir.is_dir():
            no_match.append(str(rel))
            continue

        # Foo.class, Foo$Bar.class, Foo$1.class 등
        matched = [
            f
            for f in class_dir.iterdir()
            if f.suffix == ".class"
            and (f.stem == stem or f.stem.startswith(stem + "$"))
        ]

        if not matched:
            no_match.append(str(rel))
            continue

        java_stat = java_file.stat()
        mtime = java_stat.st_mtime
        atime = java_stat.st_atime

        for class_file in matched:
            os.utime(class_file, (atime, mtime))
            copied += 1

    print(f"mtime 복사 완료: {copied}개 .class 파일")
    if no_match:
        print(f"매칭 실패: {len(no_match)}개 .java 파일")
        for f in no_match:
            print(f"  - {f}")


if __name__ == "__main__":
    if len(sys.argv) < 2 or len(sys.argv) > 3:
        print("사용법: python scripts/copy_mtime.py {project} [target_subdir]")
        sys.exit(1)
    target_subdir = sys.argv[2] if len(sys.argv) == 3 else "target/classes"
    copy_mtime(sys.argv[1], target_subdir)
