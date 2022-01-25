package edc.valutes.list

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import com.mikepenz.fastadapter.binding.AbstractBindingItem
import edc.domain.model.api.Valute
import edc.valutes.R
import edc.valutes.databinding.ValuteItemBinding
import kotlin.math.roundToInt

class ValuteItem(val valuteItem: Valute) : AbstractBindingItem<ValuteItemBinding>() {

    override val type: Int
        get() = R.id.valute_id

    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup?): ValuteItemBinding {
        return ValuteItemBinding.inflate(inflater, parent, false)
    }

    override var identifier: Long
        get() = valuteItem.id.hashCode().toLong()
        set(value) {

        }

    @SuppressLint("SetTextI18n")
    override fun bindView(binding: ValuteItemBinding, payloads: List<Any>) {
        val visibleNominal = valuteItem.nominal.takeIf { it > 1 }?.let {
            it.roundToInt().toString() + " "
        } ?: ""
        binding.code.text = valuteItem.charCode
        binding.name.text = visibleNominal + valuteItem.name
        binding.value.text = valuteItem.value.toString()
    }
}