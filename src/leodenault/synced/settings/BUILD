load("@io_bazel_rules_kotlin//kotlin:kotlin.bzl", "kt_jvm_library")

package(
    default_visibility = ["//:all_packages"],
)

kt_jvm_library(
    name = "settings",
    srcs = ["SettingsManager.kt"],
    deps = [
        ":annotations",
        "//src/leodenault/synced/coroutines",
        "//src/leodenault/synced/settings/proto:settings_java_proto",
        "//third_party/kotlin",
        "//third_party/proto",
    ],
)

kt_jvm_library(
    name = "dagger_module",
    srcs = ["SettingsDaggerModule.kt"],
    deps = [
        "annotations",
        "//third_party/dagger",
    ],
)

kt_jvm_library(
    name = "annotations",
    srcs = ["Annotations.kt"],
    deps = ["//third_party/javax:inject"],
)
