load("@rules_proto//proto:defs.bzl", "proto_library")

package(
    default_visibility = ["//:all_packages"],
)

proto_library(
    name = "settings_proto",
    srcs = ["settings.proto"],
)

java_proto_library(
    name = "settings_java_proto",
    deps = [":settings_proto"],
)
