package(
    default_visibility = ["//:all_packages"]
)

filegroup(
    name = "jlink",
    srcs = ["linux_x64/jdk-11/bin/jlink"],
)

filegroup(
    name = "jdk_linux",
    srcs = glob(include = ["linux_x64/**/*"]),
)

filegroup(
    name = "jdk_windows",
    srcs = glob(include = ["windows_x64/**/*"]),
)
