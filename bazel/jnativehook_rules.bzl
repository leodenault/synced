def natives_windows(name, jnativehook_lib_target):
    _natives(name, "windows", "JNativeHook.dll", jnativehook_lib_target)

def natives_linux(name, jnativehook_lib_target):
    _natives(name, "linux", "libJNativeHook.so", jnativehook_lib_target)

def _natives(name, platform, filename, jnativehook_lib_target):
    native.genrule(
        name = name,
        tools = [jnativehook_lib_target],
        outs = [filename],
        message = "Retrieving native JNativeHook libraries.",
        cmd = """
        TEMPDIR="$(RULEDIR)/temp"
        mkdir $$TEMPDIR
        unzip -q $(location {jnativehook_lib_target}) -d $$TEMPDIR
        cp "$$TEMPDIR/com/github/kwhat/jnativehook/lib/{platform}/x86_64/{filename}" $@
        rm -rf $$TEMPDIR
        """.format(
            jnativehook_lib_target=jnativehook_lib_target,
            platform=platform,
            filename=filename,
        ),
    )