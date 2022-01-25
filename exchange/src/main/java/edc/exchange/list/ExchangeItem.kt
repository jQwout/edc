package edc.exchange.list

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import com.mikepenz.fastadapter.binding.AbstractBindingItem
import edc.domain.model.api.Valute
import edc.exchange.R
import edc.exchange.databinding.ExchangeItemLayoutBinding

class ExchangeItem(val valuteCode: String) : AbstractBindingItem<ExchangeItemLayoutBinding>() {

    override val type: Int
        get() = R.id.exchange_valute_code_id

    override fun createBinding(
        inflater: LayoutInflater,
        parent: ViewGroup?
    ): ExchangeItemLayoutBinding {
        return ExchangeItemLayoutBinding.inflate(inflater, parent, false)
    }

    override var identifier: Long
        get() = valuteCode.hashCode().toLong()
        set(value) {

        }

    @SuppressLint("SetTextI18n")
    override fun bindView(binding: ExchangeItemLayoutBinding, payloads: List<Any>) {
        binding.root.text = valuteCode
    }
}