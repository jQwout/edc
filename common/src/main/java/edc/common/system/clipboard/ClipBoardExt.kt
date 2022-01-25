package edc.common.system.clipboard

import android.content.ClipData
import android.content.ClipboardManager
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity


fun FragmentActivity.copyToClipboard(data: String) {
    val clipboard: ClipboardManager? =
        ContextCompat.getSystemService(this, ClipboardManager::class.java)
    val clip: ClipData = ClipData.newPlainText("tedu_data", data)
    clipboard?.setPrimaryClip(clip)
}

fun Fragment.copyToClipboard(data: String) {
    requireActivity().copyToClipboard(data)
}