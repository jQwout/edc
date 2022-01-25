package edc.common.fastadapter.items

import android.view.LayoutInflater
import android.view.ViewGroup
import com.mikepenz.fastadapter.binding.AbstractBindingItem
import edc.common.R
import edc.common.databinding.EmptyLayoutItemBinding

object EmptyItem : AbstractBindingItem<EmptyLayoutItemBinding>() {

    override val type: Int
        get() = R.id.empty_item_id

    override fun createBinding(
        inflater: LayoutInflater,
        parent: ViewGroup?
    ): EmptyLayoutItemBinding {
        return EmptyLayoutItemBinding.inflate(inflater, parent, false)
    }

    override fun bindView(binding: EmptyLayoutItemBinding, payloads: List<Any>) = Unit
}