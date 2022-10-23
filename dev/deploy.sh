#!/bin/sh

echo "Building deployment targets."

./bazelisk build \
    //src/leodenault/synced:Synced_linux_x64_deploy.jar \
    //src/leodenault/synced:Synced_windows_x64_deploy.jar

CWD=$(pwd)
OUT="out"
LINUX_OUT="$OUT/linux"
WINDOWS_OUT="$OUT/windows"
JLINK="jdk/linux_x64/jdk-11/bin/jlink"

synced_bin() {
  echo "bazel-bin/src/leodenault/synced/Synced_$1_x64_deploy.jar"
}

echo "Cleaning output directory."

if [ -d $OUT ]; then
  rm -rf $OUT
fi

mkdir $OUT
mkdir $WINDOWS_OUT
mkdir "$WINDOWS_OUT/jre"
mkdir "$WINDOWS_OUT/licenses"
mkdir $LINUX_OUT
mkdir "$LINUX_OUT/jre"
mkdir "$LINUX_OUT/licenses"

cp -v $(synced_bin "linux") $LINUX_OUT
cp -v $(synced_bin "windows") $WINDOWS_OUT

cp -v scripts/Synced.sh $LINUX_OUT
cp -v scripts/Synced.bat $WINDOWS_OUT

echo "Running Java linker."

./dev/jlink.sh "$LINUX_OUT/jre" $JLINK jdk/linux_x64/jdk-11/jmods
./dev/jlink.sh "$WINDOWS_OUT/jre" $JLINK jdk/windows_x64/jdk-11/jmods

echo "Copying third party licenses."

python dev/aggregate_licenses.py third_party "$LINUX_OUT/licenses"
python dev/aggregate_licenses.py third_party "$WINDOWS_OUT/licenses"

echo "Creating compressed archives."

pushd $OUT > /dev/null
zip -r "synced_linux_x64.zip" "linux"
zip -r "synced_windows_x64.zip" "windows"

echo "Cleaning temp files."

popd > /dev/null
rm -rf $LINUX_OUT
rm -rf $WINDOWS_OUT
