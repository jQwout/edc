package edc.common.view

import android.view.View
import android.view.ViewGroup
import edc.common.context.getBottomContentTag

fun ViewGroup.findBottomContent(): View? = findViewWithTag(getBottomContentTag())
