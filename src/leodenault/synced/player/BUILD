load("@io_bazel_rules_kotlin//kotlin:kotlin.bzl", "kt_jvm_library")

package(
    default_visibility = ["//:all_packages"],
)

kt_jvm_library(
    name = "player",
    srcs = [
        "AudioSelectorView.kt",
        "NowPlayingView.kt",
        "PlayerControlsView.kt",
        "PlayerFooterView.kt",
        "PlayerHeaderView.kt",
        "PlayerPageView.kt",
    ],
    plugins = ["//third_party/compose:compose_compiler"],
    deps = [
        ":viewmodel",
        "//src/leodenault/synced/app:page",
        "//src/leodenault/synced/audioselector",
        "//src/leodenault/synced/ui",
        "//third_party/compose",
    ],
)

kt_jvm_library(
    name = "viewmodel",
    srcs = [
        "AudioSelectorViewModel.kt",
        "PlayerPageViewModel.kt",
    ],
    deps = [
        "//src/leodenault/synced/audioselector",
        "//src/leodenault/synced/desktopaudio",
        "//third_party/compose",
    ],
)

kt_jvm_library(
    name = "dagger_module",
    srcs = ["PlayerDaggerModule.kt"],
    deps = [
        "//third_party/compose",
        "//third_party/dagger",
    ],
)
