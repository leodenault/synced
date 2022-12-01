load("@io_bazel_rules_kotlin//kotlin:kotlin.bzl", "kt_compiler_plugin", "kt_jvm_library")

licenses(["notice"])  # Apache 2.0

package(
    default_visibility = ["//:all_packages"],
)

kt_jvm_library(
    name = "compose",
    plugins = [":compose_compiler"],
    exports = ["@maven//:org_jetbrains_compose_compose_full"],
)

kt_jvm_library(
    name = "compose_runtime_linux_x64",
    exports = [
        "@maven//:org_jetbrains_kotlinx_kotlinx_coroutines_swing",
        "@maven//:org_jetbrains_skiko_skiko_jvm_runtime_linux_x64",
    ],
)

kt_jvm_library(
    name = "compose_runtime_windows_x64",
    exports = [
        "@maven//:org_jetbrains_kotlinx_kotlinx_coroutines_swing",
        "@maven//:org_jetbrains_skiko_skiko_jvm_runtime_windows_x64",
    ],
)

kt_compiler_plugin(
    name = "compose_compiler",
    id = "compose_compiler",
    deps = [
        "@maven//:org_jetbrains_compose_compiler_compiler_hosted",
    ],
)

filegroup(
    name = "licenses",
    srcs = ["LICENSE.txt"],
)
