# !/bin/bash

./bazelisk build //src/leodenault/synced:Synced_windows_x64_deploy.jar
rsync bazel-bin/src/leodenault/synced/Synced_windows_x64_deploy.jar out/wsl_config_run/
cd out/wsl_config_run
../../jdk/windows_x64/jdk-11/bin/java.exe -jar ./Synced_windows_x64_deploy.jar
