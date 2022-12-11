# JNativeHook

## About

JNativeHook is a library used by Synced to include support for listening to native keyboard events.
You can [download the source code of the library](https://github.com/kwhat/jnativehook) to do with
it as you wish. The library **is covered under the LGPL license**, which is included in this
distribution.

## Modifying the Version Used by Synced

1. Extract the contents of the Synced JAR file.
2. Replace the library with your modified version. You can find the library
   under `<unzipped JAR location>\com\github\kwhat\jnativehook`.
3. Zip the modified contents back up.
4. Modify the launch script to point to your version of the application.
 