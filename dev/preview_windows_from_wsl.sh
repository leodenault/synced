# !/bin/bash

USERNAME=$(powershell.exe '$env:UserName')
TEMP_DIR="/mnt/c/Users/${USERNAME::-1}/AppData/Local/Temp"

if [ -d $TEMP_DIR/synced_preview ]; then
  rm -rf $TEMP_DIR/synced_preview
fi

./bazelisk build //src/leodenault/synced/preview:preview_pkg_windows_x64 \
  && echo "Copying executable files to '$TEMP_DIR'" \
  && unzip -q bazel-bin/src/leodenault/synced/preview/preview_pkg_windows_x64.zip -d $TEMP_DIR \
  && cd $TEMP_DIR/synced_preview \
  && ./jre/bin/java.exe -jar ./Preview_deploy.jar
