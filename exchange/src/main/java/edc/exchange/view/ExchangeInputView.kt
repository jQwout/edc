package edc.exchange.view

import android.content.Context
import android.text.InputFilter
import android.text.Spanned
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import edc.common.flow.emitState
import edc.common.view.NumberInputFilter
import edc.common.view.SimpleTextWatcher
import edc.domain.model.api.Valute
import edc.exchange.databinding.ExchangeInputViewLayoutBinding
import edc.exchange.list.ExchangeItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import java.util.regex.Matcher
import java.util.regex.Pattern

class ExchangeInputView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    val onScrolledState = MutableStateFlow<String?>(null)
    val onTextChangeState: MutableStateFlow<Double?> = MutableStateFlow(null)

    private val itemAdapter = ItemAdapter<ExchangeItem>()
    private val fastAdapter = FastAdapter.with(itemAdapter)
    private val snapHelper = PagerSnapHelper()
    private val adapterManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    private val textWatcher = SimpleTextWatcher(
        onChanged = { seq ->
            onTextChangeState.update { seq.toString().toDoubleOrNull() ?: 0.0 }
        })
    private val textSize: InputFilter = NumberInputFilter()

    private val binding =
        ExchangeInputViewLayoutBinding.inflate(LayoutInflater.from(context), this, true)


    init {
        binding.valutesChanger.adapter = fastAdapter
        binding.valutesChanger.layoutManager = adapterManager
        binding.valutesChanger.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                onScrolledState.update { itemAdapter.getAdapterItem(adapterManager.findFirstVisibleItemPosition()).valuteCode }
            }
        })
        snapHelper.attachToRecyclerView(binding.valutesChanger)
        binding.valutesInput.addTextChangedListener(textWatcher)
        binding.valutesInput.filters = arrayOf(textSize)
    }

    fun setValutes(list: List<String>) {
        itemAdapter.set(list.map(::ExchangeItem))
    }

    fun setCount(Double: Double) {
        if (binding.valutesInput.text.toString().toDoubleOrNull() == Double) return

        binding.valutesInput.removeTextChangedListener(textWatcher)
        binding.valutesInput.setText(Double.toString())
        binding.valutesInput.addTextChangedListener(textWatcher)
    }

}