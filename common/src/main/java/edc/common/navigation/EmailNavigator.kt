package edc.common.navigation

import android.content.Intent
import android.net.Uri

object EmailNavigator {

    fun openEmailApp(subject: String): Intent {
        val intent = Intent(Intent.ACTION_SENDTO)
        val data = Uri.parse("mailto:$subject")
        intent.data = data
        return intent
    }

    fun openEmailAppWithText(text: String): Intent {
        val intent = Intent(Intent.ACTION_SENDTO)
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_TEXT, text);
        return intent
    }

}