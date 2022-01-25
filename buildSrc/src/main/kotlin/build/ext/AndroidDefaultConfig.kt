package build.ext

import com.android.build.gradle.BaseExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import props.getVersionCodeInt
import props.loadVersionProperties
import java.io.File

fun BaseExtension.defaultConfig(
    versionPropertiesPath: String,
    majorVersion: Int,
    minorVersion: Int
) {
    val buildNumber =   loadVersionProperties(versionPropertiesPath).getVersionCodeInt()
    compileSdkVersion(31)
    defaultConfig {
        minSdkVersion(21)
        targetSdkVersion(30)
        versionCode = buildVersionCode(
            majorVersion,
            minorVersion,
            buildNumber
        )
        versionName = "$majorVersion.$minorVersion.$buildNumber"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables.useSupportLibrary = true

        buildConfigField(
            "long", "VERSION_CODE", (versionCode ?: 1).toString()
        )
    }

}

fun Project.configureAndroid(majorVersion: Int = 1, minorVersion: Int = 0) {
    val androidExtension = project.extensions.getByName("android")

    if (androidExtension is BaseExtension) {

        androidExtension.apply {

            defaultConfig(
                File(project.rootDir, "version.properties").path, majorVersion, minorVersion
            )

            packagingOptions {
                exclude("META-INF/NOTICE.txt")
            }

            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_1_8
                targetCompatibility = JavaVersion.VERSION_1_8
            }

            project.tasks.withType().configureEach {
                if (this is KotlinCompile) {
                    this.kotlinOptions.jvmTarget = "1.8"
                }
            }

            buildFeatures.viewBinding = true

            lintOptions.isAbortOnError = false

        }
    }
}

private fun buildVersionCode(
    majorVersion: Int,
    minorVersion: Int,
    build: Int
): Int {
    val min = (minorVersion * 100 * 1000)
    val maj = (majorVersion * 10 * 100 * 1000)
    return (maj + min + build)
}
