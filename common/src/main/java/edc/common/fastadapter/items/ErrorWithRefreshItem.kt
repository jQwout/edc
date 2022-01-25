package edc.common.fastadapter.items

import android.view.LayoutInflater
import android.view.ViewGroup
import com.mikepenz.fastadapter.binding.AbstractBindingItem
import edc.common.R
import edc.common.databinding.EmptyLayoutItemBinding
import edc.common.databinding.ErrorWithRepeatLayoutItemBinding
import edc.common.databinding.LoaderLayoutItemBinding

object ErrorWithRefreshItem : AbstractBindingItem<ErrorWithRepeatLayoutItemBinding>() {

    override val type: Int
        get() = R.id.error_with_repeat_id

    override fun createBinding(
        inflater: LayoutInflater,
        parent: ViewGroup?
    ): ErrorWithRepeatLayoutItemBinding {
        return ErrorWithRepeatLayoutItemBinding.inflate(inflater, parent, false)
    }

    override fun bindView(binding: ErrorWithRepeatLayoutItemBinding, payloads: List<Any>) = Unit
}