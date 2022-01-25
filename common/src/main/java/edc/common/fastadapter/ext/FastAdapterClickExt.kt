package edc.common.fastadapter.ext

import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.GenericItem
import com.mikepenz.fastadapter.binding.listeners.addClickListener
import edc.common.databinding.ErrorWithRepeatLayoutItemBinding

fun FastAdapter<GenericItem>.addOnRetryWhenErrorListener(block: () -> Unit) {
    addClickListener({ b: ErrorWithRepeatLayoutItemBinding -> b.repeatButton }) { v, position, fastAdapter, item ->
        block()
    }
}