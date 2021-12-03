load("@io_bazel_rules_kotlin//kotlin:kotlin.bzl", "kt_jvm_library")

package(
    default_visibility = ["//:all_packages"],
)

kt_jvm_library(
    name = "compose",
    srcs = [
        "Helpers.kt",
        "Types.kt",
    ],
    deps = [
        "//src/leodenault/synced/coroutines",
        "//third_party/compose",
        "//third_party/kotlin",
    ],
)
