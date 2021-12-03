load("@io_bazel_rules_kotlin//kotlin:kotlin.bzl", "kt_jvm_library")

package(
    default_visibility = ["//:all_packages"],
)

kt_jvm_library(
    name = "discordnavigation",
    srcs = ["DiscordNavigator.kt"],
    deps = [
        "//src/leodenault/synced/app:page",
        "//src/leodenault/synced/discord",
        "//src/leodenault/synced/navigation",
    ],
)

kt_jvm_library(
    name = "impl",
    srcs = ["DiscordNavigatorImpl.kt"],
    deps = [
        "//src/leodenault/synced/app:page",
        "//src/leodenault/synced/botaccess:bottaccess",
        "//src/leodenault/synced/botaccess:viewmodel",
        "//src/leodenault/synced/discord",
        "//src/leodenault/synced/error",
        "//src/leodenault/synced/loadingpage",
        "//src/leodenault/synced/loadingpage:viewmodel",
        "//src/leodenault/synced/serverselector",
        "//src/leodenault/synced/serverselector:viewmodel",
        "//third_party/compose",
        "//third_party/dagger",
        "//third_party/javax:inject",
    ],
)

kt_jvm_library(
    name = "dagger_module",
    srcs = ["DiscordNavigationDaggerModule.kt"],
    deps = [
        ":discordnavigation",
        ":impl",
        "//third_party/dagger",
    ],
)
