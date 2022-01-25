package edc.common.window

import android.app.Activity
import android.view.WindowManager

fun Activity.setStatusBarColorFromTheme(color: Int) {
    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
    window.statusBarColor = color
}