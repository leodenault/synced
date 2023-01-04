def synced_linux_pkg(
        name,
        binary_target,
        execution_script_target,
        jlink_package_target,
        licenses_target):
    _synced_pkg(
        name,
        "linux",
        binary_target,
        execution_script_target,
        jlink_package_target,
        licenses_target,
    )

def synced_windows_pkg(
        name,
        binary_target,
        execution_script_target,
        jlink_package_target,
        licenses_target):
    _synced_pkg(
        name,
        "windows",
        binary_target,
        execution_script_target,
        jlink_package_target,
        licenses_target,
    )

def _synced_pkg(
        name,
        platform_name,
        binary_target,
        execution_script_target,
        jlink_package_target,
        licenses_target):
    native.genrule(
        name = name,
        outs = [name + ".zip"],
        tools = [
            "//dev:aggregate_licenses",
            "//dev:deploy",
            binary_target,
            binary_target + "_deploy.jar",
            execution_script_target,
            jlink_package_target,
            licenses_target,
        ],
        message = "Outputting final package",
        cmd = """
        ./$(location //dev:deploy) \\
            $(RULEDIR) \\
            {platform_name} \\
            $(location {execution_script_target}) \\
            $(location {binary_target}_deploy.jar) \\
            $(location {licenses_target}) \\
            "$(location {jlink_package_target})" \\
            "$(location {binary_target}).runfiles/__main__/" \\
            "$@"
        """.format(
            platform_name = platform_name,
            execution_script_target = execution_script_target,
            binary_target = binary_target,
            jlink_package_target = jlink_package_target,
            licenses_target = licenses_target,
        )
    )
