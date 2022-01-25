package build.ext

import org.gradle.api.internal.BuildType
import org.gradle.api.plugins.ExtensionAware

fun BuildType.asExtAware() = this as ExtensionAware
