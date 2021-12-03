load("@io_bazel_rules_kotlin//kotlin:kotlin.bzl", "kt_jvm_library")

package(
    default_visibility = ["//:all_packages"],
)

kt_jvm_library(
    name = "discord",
    srcs = [
        "AudioPlayer.kt",
        "ConnectedClient.kt",
        "DisconnectedClient.kt",
    ],
    deps = [
        "//src/leodenault/synced/audio",
        "//src/leodenault/synced/coroutines",
        "//src/leodenault/synced/util",
        "//third_party/dagger",
        "//third_party/javax:inject",
        "//third_party/kord",
    ],
)

kt_jvm_library(
    name = "dagger_module",
    srcs = ["DiscordDaggerModule.kt"],
    deps = [
        ":discord",
        "//src/leodenault/synced/audio",
        "//src/leodenault/synced/util",
        "//third_party/dagger",
    ],
)
