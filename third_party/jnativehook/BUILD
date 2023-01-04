load("//bazel:jnativehook_rules.bzl", "natives_linux", "natives_windows")

licenses(["reciprocal"]) # LGPL

package(
    default_visibility = ["//:all_packages"],
)

alias(
    name = "jnativehook",
    actual = "@jnativehook//jar",
)

natives_linux(
    name = "natives_linux_x64",
    jnativehook_lib_target = ":jnativehook",
)

natives_windows(
    name = "natives_windows_x64",
    jnativehook_lib_target = ":jnativehook",
)

filegroup(
    name = "licenses_windows",
    srcs = [
        "LICENSE.md",
        "LICENSE_GPL.md",
        "README_WINDOWS.md",
    ],
)

filegroup(
    name = "licenses_linux",
    srcs = [
        "LICENSE.md",
        "LICENSE_GPL.md",
        "README_LINUX.md",
    ],
)
