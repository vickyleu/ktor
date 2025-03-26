/*
 * Copyright 2014-2025 JetBrains s.r.o and contributors. Use of this source code is governed by the Apache 2.0 license.
 */

import ktorbuild.createCInterop

plugins {
    id("ktorbuild.project.library")
    id("kotlinx-serialization")
    id("test-server")
}

kotlin {
    createCInterop("libcurl", sourceSet = "desktop") { target ->
        includeDirs(file("desktop/interop/include"))
        extraOpts("-libraryPath", file("desktop/interop/lib/$target"))
        // 针对 linuxArm32Hfp 添加 libz.a 的配置
        if (target == "linuxArm32Hfp") {
            // 添加 zlib 头文件路径（如果头文件不在默认路径）
            includeDirs(file("desktop/interop/include/zlib"))
            // 添加静态库路径和链接选项
            extraOpts(
                "-libraryPath", file("desktop/interop/lib/$target"),
                "-linkerOpts", "-L${file("desktop/interop/lib/$target")}",
                "-linkerOpts", "-lz"  // 或直接指定静态库: "-linkerOpts", "${file("desktop/interop/lib/$target/libz.a")}"
            )
        }
    }

    sourceSets {
        desktopMain.dependencies {
            api(project(":ktor-client:ktor-client-core"))
            api(project(":ktor-http:ktor-http-cio"))
        }
        desktopTest.dependencies {
            implementation(project(":ktor-client:ktor-client-test-base"))
            api(project(":ktor-client:ktor-client-plugins:ktor-client-logging"))
            api(project(":ktor-client:ktor-client-plugins:ktor-client-json"))
            implementation(libs.kotlinx.serialization.json)
        }
    }
}
