load("@io_bazel_rules_kotlin//kotlin:kotlin.bzl", "kt_jvm_library")

package(
    default_visibility = ["//:all_packages"],
)

kt_jvm_library(
    name = "navigation",
    srcs = ["Navigator.kt"],
    deps = [
        "//src/leodenault/synced/app:page",
        "//src/leodenault/synced/discord",
    ],
)

kt_jvm_library(
    name = "impl",
    srcs = ["NavigatorImpl.kt"],
    deps = [
        ":navigation",
        "//src/leodenault/synced/app:page",
        "//src/leodenault/synced/botaccess",
        "//src/leodenault/synced/botaccess:viewmodel",
        "//src/leodenault/synced/discord",
        "//src/leodenault/synced/error",
        "//src/leodenault/synced/loadingpage",
        "//src/leodenault/synced/loadingpage:viewmodel",
        "//src/leodenault/synced/player",
        "//src/leodenault/synced/player:viewmodel",
        "//src/leodenault/synced/settings",
        "//third_party/jnativehook",
    ],
)

kt_jvm_library(
    name = "dagger_module",
    srcs = ["NavigationDaggerModule.kt"],
    deps = [
        ":navigation",
        ":impl",
        "//third_party/dagger"
    ],
)
