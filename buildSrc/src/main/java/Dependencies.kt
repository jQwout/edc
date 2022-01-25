object Dependencies {

    const val kotlin_stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.Kotlin.kotlin}"
    const val kotlin_coroutines =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.Kotlin.coroutines}"
    const val androidx_activity_ktx = "androidx.activity:activity-ktx:${Versions.AndroidX.activity}"
    const val androidx_appcompat = "androidx.appcompat:appcompat:${Versions.AndroidX.appcompat}"
    const val androidx_core = "androidx.core:core:${Versions.AndroidX.core}"
    const val androidx_core_ktx = "androidx.core:core-ktx:${Versions.AndroidX.core}"
    const val androidx_constraint_layout =
        "androidx.constraintlayout:constraintlayout:${Versions.AndroidX.constraint_layout}"
    const val androidx_swiperefresh_layout =
        "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.AndroidX.swiperefreshlayout}"
    const val androidx_recycler = "androidx.recyclerview:recyclerview:${Versions.AndroidX.recycler}"
    const val androidx_lifecycle =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.AndroidX.lifecycle}"
    const val androidx_lifecycle_runtime =
        "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.AndroidX.lifecycle}"
    const val androidx_fragment = "androidx.fragment:fragment-ktx:${Versions.AndroidX.fragment}"
    const val androidx_navigation_ui_ktx =
        "androidx.navigation:navigation-ui-ktx:${Versions.AndroidX.navigation}"
    const val androidx_navigation_fragment_ktx =
        "androidx.navigation:navigation-fragment-ktx:${Versions.AndroidX.navigation}"
    const val androidx_work = "androidx.work:work-runtime-ktx:${Versions.AndroidX.work}"
    const val androidx_test_runner = "androidx.test:runner:${Versions.AndroidTest.runner}"
    const val androidx_test_rules = "androidx.test:rules:${Versions.AndroidTest.rules}"
    const val androidx_test_junit = "androidx.test.ext:junit:${Versions.AndroidTest.junit}"

    const val google_material = "com.google.android.material:material:${Versions.Google.material}"
    const val google_play_core = "com.google.android.play:core-ktx:${Versions.Google.play_core_ktx}"

    const val shimmer = "com.facebook.shimmer:shimmer:${Versions.Facebook.shimmer}"

    const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.Okhttp.okhttp3}"
    const val okhttp_logging_interceptor =
        "com.squareup.okhttp3:logging-interceptor:${Versions.Okhttp.okhttp3}"
    const val okhttp_mock = "com.squareup.okhttp3:mockwebserver:${Versions.Okhttp.okhttp3}"

    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.Retrofit.retrofit2}"
    const val retrofit_gson = "com.squareup.retrofit2:converter-gson:${Versions.Retrofit.retrofit2}"

    const val dagger_hilt = "com.google.dagger:hilt-android:${Versions.Hilt.hilt}"
    const val dagger_hilt_kapt = "com.google.dagger:hilt-android-compiler:${Versions.Hilt.hilt}"
    const val androidx_hilt = "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.Hilt.arch}"
    const val androidx_hilt_kapt = "androidx.hilt:hilt-compiler:${Versions.Hilt.arch_kapt}"
    const val androidx_hilt_work = "androidx.hilt:hilt-work:${Versions.Hilt.work}"

    const val fast_adapter = "com.mikepenz:fastadapter:${Versions.FastAdapter.version}"
    const val fast_adapter_diff = "com.mikepenz:fastadapter-extensions-diff:${Versions.FastAdapter.version}"
    const val fast_adapter_binding =
        "com.mikepenz:fastadapter-extensions-binding:${Versions.FastAdapter.version}"

    const val firebase_bom = "com.google.firebase:firebase-bom:${Versions.Firebase.bom}"
    const val firebase_analytics = "com.google.firebase:firebase-analytics-ktx"
    const val firebase_messaging =
        "com.google.firebase:firebase-messaging-ktx:${Versions.Firebase.messaging}"
    const val firebase_crashlytics =
        "com.google.firebase:firebase-crashlytics-ktx:${Versions.Firebase.crash}"
    const val firebase_remote_config =
        "com.google.firebase:firebase-config-ktx:${Versions.Firebase.remote_config}"

    const val junit = "junit:junit:${Versions.JUnit.jUnit4}"
    const val mockito_kotlin = "org.mockito.kotlin:mockito-kotlin:${Versions.Mockito.kotlin}"
    const val mockito_inline = "org.mockito:mockito-inline:${Versions.Mockito.inline}"
}

object Versions {

    //https://kotlinlang.org/docs/releases.html#release-details
    object Kotlin {
        const val kotlin = "1.6.10"
        const val coroutines = "1.6.0"
    }

    // https://developer.android.com/jetpack/androidx/versions
    object AndroidX {
        const val activity = "1.2.3"
        const val appcompat = "1.3.0"
        const val core = "1.5.0"
        const val recycler = "1.2.1"
        const val constraint_layout = "2.0.4"
        const val lifecycle = "2.4.0"
        const val fragment = "1.3.5"
        const val navigation = "2.3.5"
        const val swiperefreshlayout = "1.1.0"
        const val work = "2.7.1"
    }

    object Google {
        const val material = "1.4.0"
        const val play_core_ktx = "1.8.1"
    }

    object Facebook {
        const val shimmer = "0.5.0"
    }

    //https://square.github.io/okhttp/#releases
    object Okhttp {
        const val okhttp3 = "4.9.0"
    }

    //https://square.github.io/retrofit/#releases
    object Retrofit {
        const val retrofit2 = "2.9.0"
    }

    //https://developer.android.com/jetpack/androidx/releases/hilt
    object Hilt {
        const val hilt = "2.37"
        const val arch = "1.0.0-alpha03"
        const val arch_kapt = "1.0.0"
        const val work = "1.0.0"
    }

    // https://firebase.google.com/docs/crashlytics/get-started?platform=android
    object Firebase {
        const val bom = "28.4.1"
        const val messaging = "22.0.0"
        const val crash = "18.2.1"
        const val remote_config = "21.0.1"
    }

    object JUnit {
        const val jUnit4 = "4.13.2"
    }

    object FastAdapter {
        const val version = "5.5.1"
    }

    object Mockito {
        const val kotlin = "4.0.0"
        const val inline = "2.8.47"
    }

    object AndroidTest {
        const val runner = "1.4.0"
        const val rules = "1.4.0"
        const val junit = "1.1.3"
    }
}