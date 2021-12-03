load("@io_bazel_rules_kotlin//kotlin:kotlin.bzl", "kt_jvm_library")

package(
    default_visibility = ["//:all_packages"],
)

kt_jvm_library(
    name = "audio",
    srcs = [
        "AudioItemLoader.kt",
        "AudioStream.kt",
        "LoadResultHandler.kt",
    ],
    deps = [
        "//third_party/compose",
        "//third_party/dagger",
        "//third_party/javax:inject",
        "//third_party/kotlin",
        "//third_party/lavaplayer",
    ],
)

kt_jvm_library(
    name = "dagger_module",
    srcs = ["AudioDaggerModule.kt"],
    deps = [
        "//third_party/dagger",
        "//third_party/lavaplayer",
    ],
)
