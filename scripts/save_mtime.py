#!/usr/bin/env python3
"""prd/ 또는 svn/ 하위 .class 파일의 mtime을 JSON으로 스냅샷 저장

사용법: python scripts/save_mtime.py <target_dir> [subdir]
예시:   python scripts/save_mtime.py mhcweb          # mhcweb/prd (기본)
        python scripts/save_mtime.py mhcweb svn       # mhcweb/svn
"""

import os
import sys
import json
import datetime

SCRIPT_DIR = os.path.dirname(os.path.abspath(__file__))
PROJECT_ROOT = os.path.dirname(SCRIPT_DIR)


def collect_mtimes(root_dir):
    """root_dir 하위 .class 파일의 상대경로 → mtime 문자열 매핑 반환"""
    files = {}
    for dirpath, _, filenames in os.walk(root_dir):
        for f in filenames:
            if f.endswith(".class"):
                full = os.path.join(dirpath, f)
                rel = os.path.relpath(full, root_dir).replace("\\", "/")
                mtime = datetime.datetime.fromtimestamp(os.stat(full).st_mtime)
                files[rel] = mtime.strftime("%Y-%m-%d %H:%M:%S")
    return files


def main():
    if len(sys.argv) < 2:
        print("사용법: python scripts/save_mtime.py <target_dir> [subdir]")
        print("예시:   python scripts/save_mtime.py mhcweb        # mhcweb/prd (기본)")
        print("        python scripts/save_mtime.py mhcweb svn    # mhcweb/svn")
        sys.exit(1)

    target = sys.argv[1]
    subdir = sys.argv[2] if len(sys.argv) > 2 else "prd"
    scan_dir = os.path.join(PROJECT_ROOT, target, subdir)
    output_file = os.path.join(PROJECT_ROOT, target, f"{subdir}_mtime.json")

    if not os.path.isdir(scan_dir):
        print(f"오류: {scan_dir} 디렉토리가 존재하지 않습니다.")
        sys.exit(1)

    files = collect_mtimes(scan_dir)
    data = {
        "_saved_at": datetime.datetime.now().strftime("%Y-%m-%dT%H:%M:%S"),
        "files": dict(sorted(files.items())),
    }

    with open(output_file, "w", encoding="utf-8") as fp:
        json.dump(data, fp, ensure_ascii=False, indent=2)

    print(f"{subdir}_mtime.json 저장 완료: {len(files)}개 파일")
    print(f"출력: {output_file}")


if __name__ == "__main__":
    main()
