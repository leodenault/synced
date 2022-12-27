load("@io_bazel_rules_kotlin//kotlin:kotlin.bzl", "kt_jvm_library")

licenses(["notice"])  # MIT

package(
    default_visibility = ["//:all_packages"],
)

kt_jvm_library(
    name = "kord",
    exports = [
        "@maven//:dev_kord_kord_core",
        "@maven//:dev_kord_kord_core_voice",
        "@maven//:dev_kord_kord_voice",
    ],
)

filegroup(
    name = "licenses",
    srcs = ["LICENSE.txt"],
)
