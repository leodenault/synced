load("@io_bazel_rules_kotlin//kotlin:kotlin.bzl", "kt_jvm_library")

package(
    default_visibility = ["//:all_packages"],
)

kt_jvm_library(
    name = "loadingpage",
    srcs = ["LoadingPageView.kt"],
    plugins = ["//third_party/compose:compose_compiler"],
    deps = [
        ":viewmodel",
        "//third_party/compose",
    ],
)

kt_jvm_library(
    name = "viewmodel",
    srcs = ["LoadingPageViewModel.kt"],
    deps = [
        "//src/leodenault/synced/discordnavigation",
        "//third_party/javax:inject",
    ],
)

kt_jvm_library(
    name = "dagger_module",
    srcs = ["LoadingPageDaggerModule.kt"],
    deps = [
        "//src/leodenault/synced/discordnavigation",
        "//src/leodenault/synced/util",
        "//third_party/dagger",
    ],
)
