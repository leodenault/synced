load("@io_bazel_rules_kotlin//kotlin:kotlin.bzl", "kt_jvm_library")

package(
    default_visibility = ["//:all_packages"],
)

kt_jvm_library(
    name = "serverselector",
    srcs = [
        "ChannelSelectorView.kt",
        "ChannelView.kt",
        "ServerSelectableView.kt",
        "ServerSelectorHeaderView.kt",
        "ServerSelectorView.kt",
    ],
    plugins = ["//third_party/compose:compose_compiler"],
    deps = [
        ":viewmodel",
        "//src/leodenault/synced/app:page",
        "//src/leodenault/synced/audio",
        "//src/leodenault/synced/ui",
        "//third_party/compose",
    ],
)

kt_jvm_library(
    name = "viewmodel",
    srcs = [
        "ChannelViewModel.kt",
        "ServerIconStore.kt",
        "ServerSelectorViewModel.kt",
        "ServerViewModel.kt",
    ],
    deps = [
        "//src/leodenault/synced/audio",
        "//src/leodenault/synced/compose",
        "//src/leodenault/synced/coroutines",
        "//src/leodenault/synced/discord",
        "//src/leodenault/synced/discordnavigation",
        "//src/leodenault/synced/settings",
        "//third_party/compose",
        "//third_party/dagger",
        "//third_party/javax:inject",
        "//third_party/kotlin",
    ],
)

kt_jvm_library(
    name = "dagger_module",
    srcs = ["ServerSelectorDaggerModule.kt"],
    deps = [
        "//src/leodenault/synced/discord",
        "//src/leodenault/synced/util",
        "//third_party/dagger",
    ],
)
