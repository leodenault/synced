# !/bin/bash

USERNAME=$(powershell.exe '$env:UserName')
TEMP_DIR="/mnt/c/Users/${USERNAME::-1}/AppData/Local/Temp"

if [ -d $TEMP_DIR/Synced ]; then
  rm -rf $TEMP_DIR/Synced
fi

./bazelisk build //src/leodenault/synced:synced_windows_x64_dev_pkg \
  && echo "Copying executable files to '$TEMP_DIR'" \
  && unzip -q bazel-bin/src/leodenault/synced/synced_windows_x64_dev_pkg.zip -d $TEMP_DIR \
  && cd $TEMP_DIR/Synced \
  && ./jre/bin/java.exe -Djava.library.path=third_party/jnativehook -jar ./Synced_windows_x64_dev_deploy.jar
