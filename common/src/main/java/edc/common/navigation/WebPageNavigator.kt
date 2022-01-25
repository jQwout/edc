package edc.common.navigation

import android.content.Intent
import android.net.Uri


object WebPageNavigator {
    fun openInWebIntent(url: String): Intent {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        return intent
    }
}

