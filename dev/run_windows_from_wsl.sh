# !/bin/bash

USERNAME=$(powershell.exe '$env:UserName')
TEMP_DIR="/mnt/c/Users/${USERNAME::-1}/AppData/Local/Temp"

if [ -d $TEMP_DIR/Synced ]; then
  rm -rf $TEMP_DIR/Synced
fi

./bazelisk build //src/leodenault/synced:synced_windows_pkg
unzip bazel-bin/src/leodenault/synced/synced_windows_x64.zip -d $TEMP_DIR
cd $TEMP_DIR/Synced
./jre/bin/java.exe -jar ./Synced_windows_x64_deploy.jar
