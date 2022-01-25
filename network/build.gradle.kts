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
addOkhttpDeps()
addRetrofitDeps()

dependencies {
    implementation(project(mapOf("path" to ":common")))
}
