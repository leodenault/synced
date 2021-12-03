import sys
import shutil
from pathlib import Path


def output_dir_and_path(license_path):
    hierarchy = str(license_path).split("/")
    return hierarchy[-2], "/".join(hierarchy[-2:])


input_path = Path(sys.argv[1])
output_path = Path(sys.argv[2])

license_paths = Path(input_path).glob("**/LICENSE.txt")
license_path_outs = {
    path: output_dir_and_path(path)
    for path in license_paths
}

shutil.rmtree(output_path)
output_path.mkdir()

for source, (dest_dir, dest_path) in license_path_outs.items():
    output_path.joinpath(dest_dir).mkdir(parents=True, exist_ok=True)
    shutil.copy(source, output_path.joinpath(dest_path))
