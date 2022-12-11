# JNativeHook

## About

JNativeHook is a library used by Synced to include support for listening to native keyboard events.
You can [download the source code of the library](https://github.com/kwhat/jnativehook) to do with
it as you wish. The library **is covered under the LGPL license**, which is included in this
distribution.

## Modifying the Version Used by Synced

1.  Unzip the Synced JAR file.
    ```shell
    mkdir synced_modified
    unzip Synced_linux_x64_deploy.jar -d synced_modified
    ```
2.  Replace the library with your modified version.
    ```shell
    rm -rf synced_modified/com/github/kwhat/jnativehook
    cp /path/to/your/modified/version synced_modified/com/github/kwhat/jnativehook
    ```
3.  Zip the modified contents back up.
    ```shell
    zip synced_modified
    ```
4.  Modify the launch script to point to your version of the application.
 