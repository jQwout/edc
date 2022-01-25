package edc.valutes.view

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.GenericItem
import com.mikepenz.fastadapter.adapters.ItemAdapter
import com.mikepenz.fastadapter.diff.FastAdapterDiffUtil
import dagger.hilt.android.AndroidEntryPoint
import edc.common.fastadapter.ext.*
import edc.common.lang.Sort
import edc.common.view.launchAndRepeatWithViewLifecycle
import edc.common.view.setDrawableRight
import edc.valutes.R
import edc.valutes.databinding.ValutesFragmentBinding
import edc.valutes.utils.byCode
import edc.valutes.utils.byValue
import edc.valutes.viewmodel.ValuteViewModel
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ValuteFragment : Fragment(R.layout.valutes_fragment) {

    private val itemAdapter = ItemAdapter<GenericItem>()
    private val fastAdapter = FastAdapter.with(itemAdapter)
    private val viewModel: ValuteViewModel by viewModels()
    private lateinit var binding: ValutesFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = ValutesFragmentBinding.bind(view)
        binding.list.adapter = fastAdapter
        binding.list.addItemDecoration(
            DividerItemDecorator(
                ContextCompat.getDrawable(requireContext(), R.drawable.valutes_divider)!!
            )
        )

        lifecycleScope.launch {
            viewModel.state.collect {

                binding.root.isRefreshing = it is ValuteViewModel.State.Refresh

                when (it) {
                    is ValuteViewModel.State.Content -> {
                        if (it.items.isEmpty()) {
                            itemAdapter.setEmptyItem()
                        } else {
                            FastAdapterDiffUtil[itemAdapter] =
                                FastAdapterDiffUtil.calculateDiff(itemAdapter, it.items)
                        }
                    }
                    is ValuteViewModel.State.Error -> itemAdapter.setErrorItemWithRepeat(it.throwable)
                    is ValuteViewModel.State.Loading -> itemAdapter.setLoaderItem()
                    is ValuteViewModel.State.Refresh -> Unit
                }
            }
        }

        fastAdapter.addOnRetryWhenErrorListener {
            viewModel.refresh(false)
        }

        launchAndRepeatWithViewLifecycle {
            viewModel.filterFlow.collect { filter ->
                binding.valuteName.setDrawableRight(null)
                binding.valuteValue.setDrawableRight(null)

                val drawable = if (filter.ordering == Sort.Ordering.ASC) {
                    R.drawable.ic_baseline_arrow_drop_up_24
                } else {
                    R.drawable.ic_baseline_arrow_drop_down_24
                }

                if (filter.keySelector == byCode) {
                    binding.valuteName.setDrawableRight(drawable)
                } else {
                    binding.valuteValue.setDrawableRight(drawable)
                }
            }
        }

        binding.valuteName.setOnClickListener {
            viewModel.changeFilter(byCode)
        }

        binding.valuteValue.setOnClickListener {
            viewModel.changeFilter(byValue)
        }

        binding.root.setOnRefreshListener {
            viewModel.refresh(true)
        }
    }
}