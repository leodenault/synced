load("@io_bazel_rules_kotlin//kotlin:kotlin.bzl", "kt_jvm_binary", "kt_jvm_library")
load("//bazel:jdk_rules.bzl", "jlink_windows")
load("//bazel:license_rules.bzl", "aggregate_licenses")
load("//bazel:packaging_rules.bzl", "synced_windows_pkg")

kt_jvm_library(
    name = "preview",
    srcs = ["Preview.kt"],
    plugins = ["//third_party/compose:compose_compiler"],
    deps = [
        "//src/leodenault/synced/audioselector",
        "//src/leodenault/synced/compose",
        "//src/leodenault/synced/coroutines",
        "//src/leodenault/synced/player",
        "//src/leodenault/synced/channelselection:channelselection",
        "//src/leodenault/synced/ui",
        "//third_party/compose",
        "//third_party/kotlin",
    ],
)

java_binary(
    name = "Preview",
    main_class = "leodenault.synced.preview.PreviewKt",
    runtime_deps = [
        ":preview",
        "//third_party/compose:compose_runtime_windows_x64",
    ]
)

jlink_windows(
    name = "jlink_windows",
    srcs = ["//jdk:jdk_windows"],
)

aggregate_licenses(
    name = "preview_licenses",
    srcs = ["//:license"]
)

synced_windows_pkg(
    name = "preview_pkg_windows_x64",
    licenses_target = ":preview_licenses",
    jlink_package_target = ":jlink_windows",
    binary_target = ":Preview",
    execution_script_target = "//scripts:windows",
    top_level_dir_name = "synced_preview",
)
