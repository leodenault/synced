def jlink_linux(name, srcs):
    _jlink(name, srcs, "linux")

def jlink_windows(name, srcs):
    _jlink(name, srcs, "windows")

def _jlink(name, srcs, platform):
    native.genrule(
        name = name,
        outs = [platform + "_jre.zip"],
        srcs = srcs,
        tools = [
            "//jdk:jlink",
        ],
        message = "Generating JRE",
        cmd = """
        TEMP=\"$(RULEDIR)/temp\"
        JDK_OUT=\"$$TEMP/jdk\"
        JRE_OUT=\"$(RULEDIR)/temp/jre\"

        mkdir $$TEMP
        zip -q $$TEMP/jdk.zip $(SRCS)
        unzip -q $$TEMP/jdk.zip -d $$TEMP
        rm $$TEMP/jdk.zip

        $(location //jdk:jlink) \
            --add-modules java.base,java.desktop,java.logging,java.management,jdk.crypto.cryptoki \
            --output $$JRE_OUT \
            --module-path $$JDK_OUT/{platform}_x64/jdk-11/jmods

        pushd $$TEMP > /dev/null
        zip -rq jre.zip jre
        popd > /dev/null
        cp $$TEMP/jre.zip $@
        rm -rf $$TEMP
        """.format(platform = platform)
    )