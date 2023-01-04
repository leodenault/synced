load("@io_bazel_rules_kotlin//kotlin:kotlin.bzl", "kt_jvm_library")

package(
    default_visibility = ["//src/leodenault/synced:__pkg__"],
)

kt_jvm_library(
    name = "app",
    srcs = [
        "Application.kt",
        "ApplicationFrameView.kt",
    ],
    plugins = ["//third_party/compose:compose_compiler"],
    deps = [
        ":page",
        "//src/leodenault/synced/botaccess:bottaccess",
        "//src/leodenault/synced/discord",
        "//src/leodenault/synced/discordnavigation",
        "//src/leodenault/synced/error",
        "//src/leodenault/synced/loadingpage",
        "//src/leodenault/synced/loadingpage:viewmodel",
        "//src/leodenault/synced/player",
        "//src/leodenault/synced/player:viewmodel",
        "//src/leodenault/synced/playernavigation",
        "//src/leodenault/synced/serverselector",
        "//src/leodenault/synced/startup",
        "//src/leodenault/synced/util",
        "//third_party/compose",
        "//third_party/javax:inject",
        "//third_party/kotlin",
        "//third_party/jnativehook",
    ],
)

kt_jvm_library(
    name = "page",
    srcs = ["PageView.kt"],
    plugins = ["//third_party/compose:compose_compiler"],
    visibility = ["//:all_packages"],
    deps = [
        "//third_party/compose",
        "//third_party/dagger",
    ],
)

kt_jvm_library(
    name = "dagger_component",
    srcs = ["ApplicationComponent.kt"],
    deps = [
        ":app",
        "//src/leodenault/synced/audio:dagger_module",
        "//src/leodenault/synced/desktopaudio:dagger_module",
        "//src/leodenault/synced/discord:dagger_module",
        "//src/leodenault/synced/discordnavigation:dagger_module",
        "//src/leodenault/synced/error:dagger_module",
        "//src/leodenault/synced/loadingpage:dagger_module",
        "//src/leodenault/synced/player:dagger_module",
        "//src/leodenault/synced/playernavigation:dagger_module",
        "//src/leodenault/synced/serverselector:dagger_module",
        "//src/leodenault/synced/settings:dagger_module",
        "//third_party/dagger",
    ],
)
