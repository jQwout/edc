package build.ext

import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

private const val impl = "implementation"
private const val kapt = "kapt"
private const val testImpl = "testImplementation"
private const val androidTestImpl = "androidTestImplementation"


fun Project.addKotlinDeps(configurationName: String = "implementation") {
    dependencies {
        add(configurationName, Dependencies.kotlin_stdlib)
        add(configurationName, Dependencies.kotlin_coroutines)
    }
}

fun Project.addAndroidXDeps(configurationName: String = "implementation") {
    dependencies {
        add(configurationName, Dependencies.androidx_appcompat)
        add(configurationName, Dependencies.androidx_core)
        add(configurationName, Dependencies.androidx_core_ktx)
        add(configurationName, Dependencies.androidx_lifecycle)
        add(configurationName, Dependencies.androidx_lifecycle_runtime)
        add(configurationName, Dependencies.androidx_fragment)
        add(configurationName, Dependencies.androidx_recycler)
        add(configurationName, Dependencies.google_material)
        add(configurationName, Dependencies.androidx_swiperefresh_layout)
    }
}

fun Project.addNavigationDeps(configurationName: String = "implementation") {
    dependencies {
        add(configurationName, Dependencies.androidx_navigation_fragment_ktx)
        add(configurationName, Dependencies.androidx_navigation_ui_ktx)
    }
}

fun Project.addHiltDeps() {
    dependencies {
        add(impl, Dependencies.dagger_hilt)
        add(impl, Dependencies.androidx_hilt)
        add(kapt, Dependencies.dagger_hilt_kapt)
        add(kapt, Dependencies.androidx_hilt_kapt)
    }
}

fun Project.addOkhttpDeps() {
    dependencies {
        add(impl, Dependencies.okhttp_logging_interceptor)
        add(impl, Dependencies.okhttp)
    }
}

fun Project.addRetrofitDeps() {
    dependencies {
        add(impl, Dependencies.retrofit)
        add(impl, Dependencies.retrofit_gson)
    }
}

fun Project.addJunitDeps() {
    dependencies {
        add(testImpl, Dependencies.junit)
    }
}


fun Project.addMockitoDeps() {
    dependencies {
        add(testImpl, Dependencies.mockito_kotlin)
        add(testImpl, Dependencies.mockito_inline)
    }
}


fun Project.addFastAdapter() {
    dependencies {
        add(impl, Dependencies.fast_adapter)
        add(impl, Dependencies.fast_adapter_diff)
        add(impl, Dependencies.fast_adapter_binding)
    }
}

fun Project.addWorkDeps() {
    dependencies {
        add(impl, Dependencies.androidx_work)
        add(impl, Dependencies.androidx_hilt_work)
    }
}

fun Project.addAndroidTestDeps() {
    dependencies {
        add(androidTestImpl, Dependencies.androidx_test_rules)
        add(androidTestImpl, Dependencies.androidx_test_runner)
        add(androidTestImpl, Dependencies.androidx_test_junit)
    }
}