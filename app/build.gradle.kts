import build.ext.*

plugins {
    id("com.android.application")
    id("dagger.hilt.android.plugin")
}

configureKotlinPlugins()

configureAndroid(minorVersion = 1)

android {
    buildTypes {

        val SERVER_URL = "SERVER_URL"

        getByName("debug") {
            minifyEnabled(false)
            debuggable(true)
            applicationIdSuffix = ".dev"
            resValue("string", "app_name", "blueprint-dev")
            buildConfigField("String", SERVER_URL, "\"https://www.cbr-xml-daily.ru\"")
        }
        getByName("release") {
            minifyEnabled(true)
            debuggable(false)
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            resValue("string", "app_name", "blueprint")
            buildConfigField("String", SERVER_URL, "\"https://www.cbr-xml-daily.ru\"")
        }

    }

}

addAndroidXDeps()
addHiltDeps()
addKotlinDeps()
addNavigationDeps()
addHiltDeps()
addWorkDeps()

dependencies {
    implementation(project(mapOf("path" to ":valutes")))
    implementation(project(mapOf("path" to ":exchange")))
    implementation(project(mapOf("path" to ":common")))
    implementation(project(mapOf("path" to ":domain")))
    implementation(project(mapOf("path" to ":network")))
}