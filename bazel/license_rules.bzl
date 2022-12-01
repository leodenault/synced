def aggregate_licenses(name, srcs):
    native.genrule(
        name = name,
        srcs = srcs,
        outs = ["licenses.zip"],
        tools = ["//dev:aggregate_licenses"],
        message = "Aggregating licenses",
        cmd = "zip -q $@ $(SRCS)"
    )