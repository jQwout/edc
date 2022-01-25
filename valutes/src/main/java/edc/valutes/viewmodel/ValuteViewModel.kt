package edc.valutes.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.viewbinding.ViewBinding
import com.mikepenz.fastadapter.binding.AbstractBindingItem
import dagger.hilt.android.lifecycle.HiltViewModel
import edc.common.lang.Sort
import edc.domain.model.api.Valute
import edc.domain.model.repo.ValuteRepository
import edc.valutes.list.ValuteItem
import edc.valutes.utils.byValue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class ValuteViewModel @Inject constructor(private val valuteRepository: ValuteRepository) :
    ViewModel() {

    private val updateUIFlow: MutableStateFlow<UpdateUIData> =
        MutableStateFlow(
            UpdateUIData(
                Sort(ordering = Sort.Ordering.ASC, keySelector = byValue),
                false,
                true
            )
        )

    val filterFlow = updateUIFlow.map { it.sort }

    val state: Flow<State> = updateUIFlow
        .transform {
            try {
                if (it.isRefresh) {
                    emit(State.Refresh)
                } else if (it.isFirstLoading) {
                    emit(State.Loading)
                }
                emit(State.Content(valuteRepository.getValutes(it.sort).map(::ValuteItem)))
            } catch (e: Exception) {
                emit(State.Error(e))
            }
        }
        .flowOn(Dispatchers.IO)
        .shareIn(CoroutineScope(Dispatchers.IO), SharingStarted.Eagerly, 0)


    fun refresh(isSwipeToRefresh: Boolean = true) {
        updateUIFlow.update {
            it.change(isSwipeToRefresh)
        }
    }

    fun changeFilter(keySelector: (Valute) -> Comparable<*>) {
        updateUIFlow.update {
            it.change(
                if (it.sort.keySelector !== keySelector) {
                    Sort(
                        ordering = it.sort.ordering,
                        keySelector = keySelector
                    )
                } else {
                    Sort(
                        ordering = if (it.sort.ordering === Sort.Ordering.DESC) Sort.Ordering.ASC else Sort.Ordering.DESC,
                        keySelector = it.sort.keySelector
                    )
                }
            )
        }
    }

    class UpdateUIData(
        val sort: Sort<Valute>,
        val isRefresh: Boolean,
        val isFirstLoading: Boolean = false
    ) {
        fun change(sort: Sort<Valute>) = UpdateUIData(sort, false)
        fun change(isRefresh: Boolean) = UpdateUIData(sort, isRefresh)
    }

    sealed class State {

        object Loading : State()
        object Refresh : State()
        class Content(
            val items: List<AbstractBindingItem<out ViewBinding>>
        ) : State()

        class Error(val throwable: Throwable) : State()
    }
}