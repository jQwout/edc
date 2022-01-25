package edc.common.app

import android.app.Activity
import android.app.Application
import android.content.IntentFilter
import android.net.ConnectivityManager
import edc.common.broadcast.ConnectionChangeReceiver

abstract class CommonAppWrapper : Application() {

    var activity: Activity? = null
    var hasConnection: Boolean = true

    open val callback = CommonActivityLifecycleCallbacks(this)
    private val connectionChangeReceiver = ConnectionChangeReceiver()

    override fun onCreate() {
        super.onCreate()

        registerConnectionChangeReceiver()
        registerActivityLifecycleCallbacks(callback)
    }

    private fun registerConnectionChangeReceiver() {
        val filter = IntentFilter()
        filter.addAction(ConnectivityManager.EXTRA_NO_CONNECTIVITY)
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION)
        registerReceiver(connectionChangeReceiver, filter)
    }
}