import build.ext.*

plugins {
    id("com.android.library")
    id("kotlin-android")
}

configureAndroid()

addAndroidXDeps()
addKotlinDeps()
addJunitDeps()
addRetrofitDeps()
addWorkDeps()
addFastAdapter()
