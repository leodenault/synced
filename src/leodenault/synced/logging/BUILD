load("@io_bazel_rules_kotlin//kotlin:kotlin.bzl", "kt_jvm_library")

package(
    default_visibility = ["//:all_packages"]
)

kt_jvm_library(
    name = "logging",
    srcs = ["LoggingConfiguration.kt"],
    deps = [
        "//third_party/dagger",
        "//third_party/javax:inject"
    ],
)

kt_jvm_library(
    name = "dagger_component",
    srcs = ["LoggingComponent.kt"],
    deps = [
        ":dagger_module",
        ":logging",
        "//third_party/dagger"
    ],
)

kt_jvm_library(
    name = "dagger_module",
    srcs = ["LoggingDaggerModule.kt"],
    deps = ["//third_party/dagger"],
)

kt_jvm_library(
    name = "dagger_component_dev",
    srcs = ["LoggingDevComponent.kt"],
    deps = [
        ":dagger_module",
        ":dagger_module_dev",
        ":logging",
        "//third_party/dagger"
    ],
)

kt_jvm_library(
    name = "dagger_module_dev",
    srcs = ["LoggingDevDaggerModule.kt"],
    deps = ["//third_party/dagger"],
)
