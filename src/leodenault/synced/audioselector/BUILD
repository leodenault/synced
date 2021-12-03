load("@io_bazel_rules_kotlin//kotlin:kotlin.bzl", "kt_jvm_library")

package(
    default_visibility = ["//:all_packages"],
)

kt_jvm_library(
    name = "audioselector",
    srcs = [
        "AudioTrackView.kt",
    ],
    plugins = ["//third_party/compose:compose_compiler"],
    deps = [
        ":viewmodel",
        "//src/leodenault/synced/ui",
        "//third_party/compose",
    ],
)

kt_jvm_library(
    name = "viewmodel",
    srcs = ["AudioTrackViewModel.kt"],
    deps = [
        "//src/leodenault/synced/audio",
        "//src/leodenault/synced/coroutines",
        "//src/leodenault/synced/util",
        "//third_party/kotlin",
    ],
)
