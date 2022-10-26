load("@io_bazel_rules_kotlin//kotlin:kotlin.bzl", "kt_jvm_binary", "kt_jvm_library")

kt_jvm_library(
    name = "preview",
    srcs = ["Preview.kt"],
    plugins = ["//third_party/compose:compose_compiler"],
    deps = [
        "//src/leodenault/synced/audioselector",
        "//src/leodenault/synced/compose",
        "//src/leodenault/synced/coroutines",
        "//src/leodenault/synced/player",
        "//src/leodenault/synced/serverselector",
        "//src/leodenault/synced/ui",
        "//third_party/compose",
        "//third_party/kotlin",
    ],
)

kt_jvm_binary(
    name = "Preview",
    main_class = "leodenault.synced.preview.PreviewKt",
    runtime_deps = [
        ":preview",
        "//third_party/compose:compose_runtime_linux_x64",
    ],
)
