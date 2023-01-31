load("@io_bazel_rules_kotlin//kotlin:kotlin.bzl", "kt_jvm_library")

package(
    default_visibility = ["//:all_packages"],
)

kt_jvm_library(
    name = "startup",
    srcs = ["Startup.kt"],
    deps = [
        "//src/leodenault/synced/desktopaudio",
        "//src/leodenault/synced/discord",
        "//src/leodenault/synced/navigation",
        "//third_party/dagger",
        "//third_party/javax:inject",
    ],
)
