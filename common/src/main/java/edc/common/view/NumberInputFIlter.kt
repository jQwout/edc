package edc.common.view

import android.text.InputFilter
import android.text.Spanned

class NumberInputFilter() : InputFilter {
    val integer = "^[0-9]+([.][0-9]{0,5})?\$".toRegex()
    val decimal = "^\\d+\\$".toRegex()


    override fun filter(
        source: CharSequence?,
        start: Int,
        end: Int,
        dest: Spanned?,
        dstart: Int,
        dend: Int
    ): CharSequence? {
        val str = dest.toString() + source.toString()

        if (integer.matches(str) || decimal.matches(str)) {
            return null
        }
        return ""
    }
}