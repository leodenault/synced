load("@io_bazel_rules_kotlin//kotlin:kotlin.bzl", "kt_jvm_library")

package(
    default_visibility = ["//:all_packages"],
)

kt_jvm_library(
    name = "playernavigation",
    srcs = ["PlayerNavigator.kt"],
    deps = ["//src/leodenault/synced/navigation"],
)

kt_jvm_library(
    name = "impl",
    srcs = ["PlayerNavigatorImpl.kt"],
    deps = [
        ":playernavigation",
        "//src/leodenault/synced/app:page",
        "//src/leodenault/synced/audioselector",
        "//src/leodenault/synced/player",
        "//src/leodenault/synced/util",
        "//third_party/compose",
        "//third_party/dagger",
        "//third_party/javax:inject",
    ],
)

kt_jvm_library(
    name = "dagger_module",
    srcs = ["PlayerNavigationDaggerModule.kt"],
    deps = [
        ":impl",
        ":playernavigation",
        "//third_party/dagger",
    ],
)
