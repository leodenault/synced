load("@io_bazel_rules_kotlin//kotlin:kotlin.bzl", "kt_jvm_library")

package(
    default_visibility = ["//:all_packages"],
)

kt_jvm_library(
    name = "bottaccess",
    srcs = ["BotAccessPageView.kt"],
    plugins = ["//third_party/compose:compose_compiler"],
    deps = [
        ":viewmodel",
        "//third_party/compose",
    ],
)

kt_jvm_library(
    name = "viewmodel",
    srcs = ["BotAccessPageViewModel.kt"],
    deps = [
        "//src/leodenault/synced/coroutines",
        "//src/leodenault/synced/discord",
        "//src/leodenault/synced/discordnavigation",
        "//src/leodenault/synced/settings",
        "//third_party/javax:inject",
        "//third_party/kotlin",
    ],
)
