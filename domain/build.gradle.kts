import build.ext.*

plugins {
    id("com.android.library")
    id("dagger.hilt.android.plugin")
}

configureKotlinPlugins()
configureAndroid()

addHiltDeps()
addKotlinDeps()
addRetrofitDeps()

dependencies {
    implementation(project(mapOf("path" to ":common")))
    implementation(project(mapOf("path" to ":network")))
}
