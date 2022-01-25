package edc.common.navigation

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager

object RestartAppDelegate {

    fun execute(context: Context) {
        val packageManager: PackageManager = context.packageManager
        val intent = checkNotNull(packageManager.getLaunchIntentForPackage(context.packageName))
        val componentName = intent.component
        val mainIntent = Intent.makeRestartActivityTask(componentName)
        context.startActivity(mainIntent)
        Runtime.getRuntime().exit(0)
    }
}


fun closeApp() = Runtime.getRuntime().exit(0)