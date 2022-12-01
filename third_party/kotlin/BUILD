load("@io_bazel_rules_kotlin//kotlin:core.bzl", "define_kt_toolchain")
load("@io_bazel_rules_kotlin//kotlin:kotlin.bzl", "kt_jvm_library")

licenses(["notice"])  # Apache 2.0

package(
    default_visibility = ["//:all_packages"],
)

define_kt_toolchain(
    name = "kotlin_toolchain",
    api_version = "1.5",  # "1.1", "1.2", "1.3", "1.4", or "1.5"
    jvm_target = "11",  # "1.6", "1.8", "9", "10", "11", "12", or "13",
    language_version = "1.5",  # "1.1", "1.2", "1.3", "1.4", or "1.5"
)

kt_jvm_library(
    name = "kotlin",
    exports = ["@maven//:org_jetbrains_kotlinx_kotlinx_coroutines_android"],
)

filegroup(
    name = "licenses",
    srcs = ["LICENSE.txt"],
)
