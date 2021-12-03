load("@io_bazel_rules_kotlin//kotlin:kotlin.bzl", "kt_jvm_library")

package(
    default_visibility = ["//:all_packages"],
)

kt_jvm_library(
    name = "desktopaudio",
    srcs = [
        "AudioDirectoryFile.kt",
        "DesktopAudioListLoader.kt",
    ],
    deps = [
        "//src/leodenault/synced/coroutines",
        "//src/leodenault/synced/settings",
        "//src/leodenault/synced/util",
        "//third_party/dagger",
        "//third_party/javax:inject",
        "//third_party/kotlin",
    ],
)

kt_jvm_library(
    name = "dagger_module",
    srcs = ["DesktopAudioDaggerModule.kt"],
    deps = [
        "//src/leodenault/synced/util",
        "//third_party/dagger",
    ],
)
