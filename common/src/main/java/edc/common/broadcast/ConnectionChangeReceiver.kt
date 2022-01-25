package edc.common.broadcast

import android.net.ConnectivityManager
import android.content.Intent
import android.content.BroadcastReceiver
import android.content.Context
import android.net.NetworkCapabilities
import android.os.Build
import androidx.core.content.ContextCompat.getSystemService
import com.google.android.material.snackbar.Snackbar
import edc.common.app.CommonAppWrapper
import android.view.Gravity

import android.widget.FrameLayout
import edc.common.metrics.dp


class ConnectionChangeReceiver : BroadcastReceiver() {

    private var snackbar: Snackbar? = null

    override fun onReceive(context: Context, intent: Intent?) {
        val app = context.applicationContext as CommonAppWrapper
        val hasConnection = hasConnection(context)
        app.hasConnection = hasConnection
        if (hasConnection.not()) {
            app.activity?.window?.decorView?.let {
                val snack = Snackbar
                    .make(it, "No internet connection", Snackbar.LENGTH_INDEFINITE)

                snackbar = snack
                val view = snack.view
                val params = view.layoutParams as FrameLayout.LayoutParams
                params.topMargin = 32.dp
                params.gravity = Gravity.TOP
                view.layoutParams = params
                snack.show()
            }
        } else {
            if (snackbar?.isShown == true) {
                snackbar?.dismiss()
            }
        }
    }

    private fun hasConnection(context: Context): Boolean {
        var isConnected = false
        val connectivityManager = getSystemService(context, ConnectivityManager::class.java)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val networkCapabilities = connectivityManager?.activeNetwork ?: return false
            val activeNetwork =
                connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
            isConnected = when {
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            connectivityManager?.activeNetworkInfo?.run {
                isConnected = when (type) {
                    ConnectivityManager.TYPE_WIFI -> true
                    ConnectivityManager.TYPE_MOBILE -> true
                    ConnectivityManager.TYPE_ETHERNET -> true
                    else -> false
                }
            } ?: return false
        }
        return isConnected
    }
}