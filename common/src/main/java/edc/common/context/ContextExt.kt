package edc.common.context

import android.content.Context
import android.util.TypedValue
import android.view.View
import edc.common.R

fun Context.getAttrColor(vararg attr: Int): Int {
    val typedValue = TypedValue();

    val arr = obtainStyledAttributes(typedValue.data, attr);
    val color = arr.getColor(0, 0);

    arr.recycle();

    return color
}

fun Context.getAccentColor() = getAttrColor(R.attr.colorAccent)

fun Context.getBottomContentTag() = getString(R.string.bottom_content_tag)
fun View.getBottomContentTag() = context.getBottomContentTag()