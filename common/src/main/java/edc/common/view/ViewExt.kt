package edc.common.view

import android.text.Editable
import android.text.TextWatcher
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat

fun TextView.setDrawableRight(@DrawableRes drawableId: Int? = null) {
    setCompoundDrawablesWithIntrinsicBounds(
        null,
        null,
        drawableId?.let {
            ContextCompat.getDrawable(context, it)
        },
        null
    )
}


fun SimpleTextWatcher(
    onChanged: (CharSequence?) -> Unit,
) = object : TextWatcher {
    override fun afterTextChanged(s: Editable?) {

    }

    override fun beforeTextChanged(text: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(text: CharSequence?, start: Int, before: Int, count: Int) {
        onChanged(text)
    }
}