package edc.common.fastadapter.items

import android.view.LayoutInflater
import android.view.ViewGroup
import com.mikepenz.fastadapter.binding.AbstractBindingItem
import edc.common.R
import edc.common.databinding.EmptyLayoutItemBinding
import edc.common.databinding.LoaderLayoutItemBinding

object LoaderItem : AbstractBindingItem<LoaderLayoutItemBinding>() {

    override val type: Int
        get() = R.id.loader_item_id

    override fun createBinding(
        inflater: LayoutInflater,
        parent: ViewGroup?
    ): LoaderLayoutItemBinding {
        return LoaderLayoutItemBinding.inflate(inflater, parent, false)
    }

    override fun bindView(binding: LoaderLayoutItemBinding, payloads: List<Any>) = Unit
}