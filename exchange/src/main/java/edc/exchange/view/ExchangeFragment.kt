package edc.exchange.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import edc.common.view.launchAndRepeatWithViewLifecycle
import edc.exchange.R
import edc.exchange.databinding.ExchangeFragmentBinding
import edc.exchange.viewmodel.ExchangeViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filterNotNull

@AndroidEntryPoint
class ExchangeFragment : Fragment(R.layout.exchange_fragment) {

    private val viewModel: ExchangeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = ExchangeFragmentBinding.bind(view)

        launchAndRepeatWithViewLifecycle {
            viewModel.baseValuteState.collect {
                binding.rubInput.setValutes(it)
            }
        }

        launchAndRepeatWithViewLifecycle {
            viewModel.otherValutesState.collect {
                binding.otherInput.setValutes(it)
            }
        }

        launchAndRepeatWithViewLifecycle {
            viewModel.scoreFlow.collect { value ->
                binding.rubInput.setCount(value.mainValue)
                binding.otherInput.setCount(value.secondValue)
            }
        }

        lifecycleScope.launchWhenCreated {
            binding.rubInput.onTextChangeState.collectLatest {
                delay(300)
                viewModel.emitValues(it ?: 0.0, null)
            }
        }

        lifecycleScope.launchWhenCreated {
            binding.otherInput.onTextChangeState.collectLatest {
                delay(300)
                viewModel.emitValues(null, it ?: 0.0)
            }
        }

        lifecycleScope.launchWhenCreated {
            binding.rubInput.onScrolledState.filterNotNull().collect {
                viewModel.emitBaseCode(it)
            }
        }

        lifecycleScope.launchWhenCreated {
            binding.otherInput.onScrolledState.filterNotNull().collect {
                viewModel.emitOtherCode(it)
            }
        }
    }
}