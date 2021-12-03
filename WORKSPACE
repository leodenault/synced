load("@bazel_tools//tools/build_defs/repo:git.bzl", "git_repository")
load("@bazel_tools//tools/build_defs/repo:http.bzl", "http_archive", "http_file")
load("@bazel_tools//tools/build_defs/repo:maven_rules.bzl", "maven_jar")

#############################################################
# Protobuf
#############################################################

http_archive(
    name = "com_google_protobuf",
    sha256 = "c10405fb99b361388d8dbfbe5fe43ef3c53a06dfd6a9fa8c33e70c34d243b044",
    strip_prefix = "protobuf-3.19.1",
    urls = [
        "https://github.com/protocolbuffers/protobuf/releases/download/v3.19.1/protobuf-java-3.19.1.tar.gz",
    ],
)

load("@com_google_protobuf//:protobuf_deps.bzl", "protobuf_deps")

protobuf_deps()

#############################################################
# Bazel proto rules
#############################################################

http_archive(
    name = "rules_java",
    sha256 = "ccf00372878d141f7d5568cedc4c42ad4811ba367ea3e26bc7c43445bbc52895",
    strip_prefix = "rules_java-d7bf804c8731edd232cb061cb2a9fe003a85d8ee",
    urls = [
        "https://mirror.bazel.build/github.com/bazelbuild/rules_java/archive/d7bf804c8731edd232cb061cb2a9fe003a85d8ee.tar.gz",
        "https://github.com/bazelbuild/rules_java/archive/d7bf804c8731edd232cb061cb2a9fe003a85d8ee.tar.gz",
    ],
)

http_archive(
    name = "rules_proto",
    sha256 = "2490dca4f249b8a9a3ab07bd1ba6eca085aaf8e45a734af92aad0c42d9dc7aaf",
    strip_prefix = "rules_proto-218ffa7dfa5408492dc86c01ee637614f8695c45",
    urls = [
        "https://mirror.bazel.build/github.com/bazelbuild/rules_proto/archive/218ffa7dfa5408492dc86c01ee637614f8695c45.tar.gz",
        "https://github.com/bazelbuild/rules_proto/archive/218ffa7dfa5408492dc86c01ee637614f8695c45.tar.gz",
    ],
)

load("@rules_java//java:repositories.bzl", "rules_java_dependencies", "rules_java_toolchains")

rules_java_dependencies()

rules_java_toolchains()

load("@rules_proto//proto:repositories.bzl", "rules_proto_dependencies", "rules_proto_toolchains")

rules_proto_dependencies()

rules_proto_toolchains()

#############################################################
# Load common Bazel targets defined by Google.
#
# MAKE SURE TO LOAD ANY CONFLICTING LIBRARIES BEFORE THIS OR
# ELSE VERY CONFUSING ERRORS WILL OCCUR.
#############################################################
http_archive(
    name = "google_bazel_common",
    strip_prefix = "bazel-common-76d25d1921c2534c7654aebb2e7cf687cfb469aa",
    urls = ["https://github.com/google/bazel-common/archive/76d25d1921c2534c7654aebb2e7cf687cfb469aa.zip"],
)

load("@google_bazel_common//:workspace_defs.bzl", "google_common_workspace_rules")

google_common_workspace_rules()

#############################################################
# Dagger
#############################################################

DAGGER_TAG = "2.39.1"

DAGGER_SHA = "b368db2a73d4781eecb535bb58223991245237b654cb1491328630f2263eb441"

http_archive(
    name = "google_dagger",
    sha256 = DAGGER_SHA,
    strip_prefix = "dagger-dagger-%s" % DAGGER_TAG,
    urls = ["https://github.com/google/dagger/archive/dagger-%s.zip" % DAGGER_TAG],
)

load("@google_dagger//:workspace_defs.bzl", "DAGGER_ARTIFACTS", "DAGGER_REPOSITORIES")

#############################################################
# Maven Dependencies
#############################################################

RULES_JVM_EXTERNAL_TAG = "3.1"

RULES_JVM_EXTERNAL_SHA = "e246373de2353f3d34d35814947aa8b7d0dd1a58c2f7a6c41cfeaff3007c2d14"

http_archive(
    name = "rules_jvm_external",
    sha256 = RULES_JVM_EXTERNAL_SHA,
    strip_prefix = "rules_jvm_external-%s" % RULES_JVM_EXTERNAL_TAG,
    url = "https://github.com/bazelbuild/rules_jvm_external/archive/%s.zip" % RULES_JVM_EXTERNAL_TAG,
)

load("@rules_jvm_external//:defs.bzl", "maven_install")

COMPOSE_VERSION = "1.0.0-alpha3"

maven_install(
    artifacts = [
        "com.sedmelluq:lavaplayer:1.3.77",
        "dev.kord:kord-core:0.8.0-M5",
        "org.jetbrains.compose:compose-full:%s" % COMPOSE_VERSION,
        "org.jetbrains.compose.compiler:compiler-hosted:%s" % COMPOSE_VERSION,
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.2",
        "org.jetbrains.kotlinx:kotlinx-coroutines-swing:1.5.1",  # Required to satisfy compose dependencies.
        "org.jetbrains.skiko:skiko-jvm-runtime-linux-x64:0.3.17",  # Required to satisfy compose dependencies.
        "org.jetbrains.skiko:skiko-jvm-runtime-windows-x64:0.3.17",  # Required to satisfy compose dependencies.
    ] + DAGGER_ARTIFACTS,
    fail_on_missing_checksum = False,
    fetch_sources = True,
    repositories = [
        "https://repo1.maven.org/maven2",
        "https://maven.pkg.jetbrains.space/public/p/compose/dev",
        "https://m2.dv8tion.net/releases",
    ] + DAGGER_REPOSITORIES,
)

#############################################################
# Kotlin
#############################################################

KOTLIN_RULES_VERSION = "1.5.0-beta-4"

http_archive(
    name = "io_bazel_rules_kotlin",
    urls = ["https://github.com/bazelbuild/rules_kotlin/releases/download/v%s/rules_kotlin_release.tgz" % KOTLIN_RULES_VERSION],
)

load("@io_bazel_rules_kotlin//kotlin:repositories.bzl", "kotlin_repositories", "kotlinc_version")

kotlin_repositories(
    compiler_release = kotlinc_version(
        release = "1.5.21",
        sha256 = "f3313afdd6abf1b8c75c6292f4e41f2dbafefc8f6c72762c7ba9b3daeef5da59",
    ),
)

register_toolchains("//third_party/kotlin:kotlin_toolchain")
