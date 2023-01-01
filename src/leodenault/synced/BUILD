load("@io_bazel_rules_kotlin//kotlin:kotlin.bzl", "kt_jvm_binary", "kt_jvm_library")
load("//bazel:jdk_rules.bzl", "jlink_linux", "jlink_windows")
load("//bazel:license_rules.bzl", "aggregate_licenses")
load("//bazel:packaging_rules.bzl", "synced_linux_pkg", "synced_windows_pkg")

kt_jvm_library(
    name = "main",
    srcs = ["Main.kt"],
    deps = [
        "//src/leodenault/synced/app:dagger_component",
        "//src/leodenault/synced/logging:dagger_component",
    ],
)

kt_jvm_library(
    name = "main_dev",
    srcs = ["MainDev.kt"],
    deps = [
        "//src/leodenault/synced/app:dagger_component",
        "//src/leodenault/synced/logging:dagger_component_dev",
    ],
)

java_binary(
    name = "Synced_linux_x64",
    main_class = "leodenault.synced.MainKt",
    runtime_deps = [
        ":main",
        "//third_party/compose:compose_runtime_linux_x64",
        "//third_party/slf4j",
    ],
)

java_binary(
    name = "Synced_linux_x64_dev",
    main_class = "leodenault.synced.MainDevKt",
    runtime_deps = [
        ":main_dev",
        "//third_party/compose:compose_runtime_linux_x64",
        "//third_party/slf4j",
    ],
)

java_binary(
    name = "Synced_windows_x64",
    main_class = "leodenault.synced.MainKt",
    runtime_deps = [
        ":main",
        "//third_party/compose:compose_runtime_windows_x64",
        "//third_party/slf4j",
    ],
)

java_binary(
    name = "Synced_windows_x64_dev",
    main_class = "leodenault.synced.MainDevKt",
    runtime_deps = [
        ":main_dev",
        "//third_party/compose:compose_runtime_windows_x64",
        "//third_party/slf4j",
    ],
)

aggregate_licenses(
    name = "licenses",
    srcs = [
        "//third_party/compose:licenses",
        "//third_party/dagger:licenses",
        "//third_party/javax:licenses",
        "//third_party/kord:licenses",
        "//third_party/kotlin:licenses",
        "//third_party/lavaplayer:licenses",
        "//third_party/proto:licenses",
        "//third_party/slf4j:licenses",
    ],
)

jlink_linux(
    name = "jlink_linux",
    srcs = ["//jdk:jdk_linux"],
)

synced_linux_pkg(
    name = "synced_linux_x64_pkg",
    binary_target = ":Synced_linux_x64_deploy.jar",
    execution_script_target = "//scripts:linux",
    jlink_package_target = ":jlink_linux",
    licenses_target = ":licenses",
)

jlink_windows(
    name = "jlink_windows",
    srcs = ["//jdk:jdk_windows"],
)

synced_windows_pkg(
    name = "synced_windows_x64_pkg",
    binary_target = ":Synced_windows_x64_deploy.jar",
    execution_script_target = "//scripts:windows",
    jlink_package_target = ":jlink_windows",
    licenses_target = ":licenses",
)

synced_windows_pkg(
    name = "synced_windows_x64_dev_pkg",
    binary_target = ":Synced_windows_x64_dev_deploy.jar",
    execution_script_target = "//scripts:windows",
    jlink_package_target = ":jlink_windows",
    licenses_target = ":licenses",
)
