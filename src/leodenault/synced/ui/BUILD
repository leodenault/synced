load("@io_bazel_rules_kotlin//kotlin:kotlin.bzl", "kt_jvm_library")

package(
    default_visibility = ["//:all_packages"],
)

kt_jvm_library(
    name = "ui",
    srcs = [
        "FileFinder.kt",
        "LoadingColumn.kt",
        "PageHeader.kt",
        "SearchBox.kt",
        "SelectableItem.kt",
        "TabView.kt",
    ],
    plugins = ["//third_party/compose:compose_compiler"],
    deps = ["//third_party/compose"],
)
