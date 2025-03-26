/*
 * Copyright 2014-2024 JetBrains s.r.o and contributors. Use of this source code is governed by the Apache 2.0 license.
 */

package ktorbuild.targets

import ktorbuild.internal.kotlin
import ktorbuild.internal.libs
import org.gradle.api.Project
import org.gradle.kotlin.dsl.invoke

internal fun Project.configureCommon() {
    kotlin {
        sourceSets {
            commonMain.dependencies {
                api(libs.kotlinx.coroutines.core)
            }

            commonTest.dependencies {
                implementation(libs.kotlin.test)
            }
        }
    }
    configurations.all {
        resolutionStrategy.eachDependency {
            if (requested.group == "org.jetbrains.kotlinx" && requested.module.name.startsWith("kotlinx-coroutines")){
                this.useTarget("com.vickyleu.kotlinx.coroutines:${requested.module.name}:1.10.1-SNAPSHOT")
            }else if (requested.group == "org.jetbrains.kotlinx" && requested.module.name.startsWith("kotlinx-html")){
                this.useTarget("com.vickyleu.kotlinx:${requested.module.name}:0.12.0")
            }else if (requested.group == "io.ktor" && requested.module.name.startsWith("ktor")){
                this.useTarget("com.vickyleu.ktor:${requested.module.name}:3.1.2-SNAPSHOT")
            }else if (requested.group == "io.github.oshai"){
                this.useTarget("com.vickyleu.oshai:${requested.module.name}:7.0.6")
            }
        }
    }
}
