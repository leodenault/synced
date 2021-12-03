load("@io_bazel_rules_kotlin//kotlin:kotlin.bzl", "kt_jvm_binary", "kt_jvm_library")

kt_jvm_library(
    name = "main",
    srcs = ["Main.kt"],
    deps = [
        "//src/leodenault/synced/app:dagger_component",
    ],
)

java_binary(
    name = "Synced_linux_x64",
    main_class = "leodenault.synced.MainKt",
    runtime_deps = [
        ":main",
        "//third_party/compose:compose_runtime_linux_x64",
    ],
)

java_binary(
    name = "Synced_windows_x64",
    main_class = "leodenault.synced.MainKt",
    runtime_deps = [
        ":main",
        "//third_party/compose:compose_runtime_windows_x64",
    ],
)
