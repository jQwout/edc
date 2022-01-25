package build.ext

import org.gradle.api.Project
import com.android.build.api.dsl.AndroidSourceSet
import com.android.build.gradle.BaseExtension

fun Project.applyFeatureSourceSet(block: (AndroidSourceSet) -> Unit) {
    val androidExtension = project.extensions.getByName("android")
    if (androidExtension is BaseExtension) {
        block(androidExtension.sourceSets.getByName("debug"))
        block(androidExtension.sourceSets.getByName("release"))
    }
}