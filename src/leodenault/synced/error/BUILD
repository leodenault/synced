load("@io_bazel_rules_kotlin//kotlin:kotlin.bzl", "kt_jvm_library")

package(
    default_visibility = ["//:all_packages"],
)

kt_jvm_library(
    name = "error",
    srcs = ["ErrorPageView.kt"],
    plugins = ["//third_party/compose:compose_compiler"],
    deps = [
        "//src/leodenault/synced/app:page",
        "//third_party/compose",
    ],
)

kt_jvm_library(
    name = "annotations",
    srcs = ["Annotations.kt"],
    deps = ["//third_party/javax:inject"],
)
