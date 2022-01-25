package build.ext

import org.gradle.api.Project

fun Project.configureKotlinPlugins() {
    plugins.apply("kotlin-android")
    plugins.apply("kotlin-kapt")
}

fun Project.configureKotlinParcelizePlugins() {
    plugins.apply("kotlin-android")
    plugins.apply("kotlin-kapt")
    plugins.apply("kotlin-parcelize")
}

fun Project.configureKotlinHiltPlugins() {
    plugins.apply("kotlin-android")
    plugins.apply("kotlin-kapt")
    plugins.apply("dagger.hilt.android.plugin")
}