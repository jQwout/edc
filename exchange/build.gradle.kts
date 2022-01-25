import build.ext.*

plugins {
    id("com.android.library")
    id("dagger.hilt.android.plugin")
}

configureKotlinPlugins()
configureAndroid()

addAndroidXDeps()
addHiltDeps()
addKotlinDeps()
addNavigationDeps()
addFastAdapter()
addJunitDeps()

dependencies {
    implementation(project(mapOf("path" to ":common")))
    implementation(project(mapOf("path" to ":network")))
    implementation(project(mapOf("path" to ":domain")))
}
