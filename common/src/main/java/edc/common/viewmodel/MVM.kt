package edc.common.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edc.common.viewmodel.events.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

abstract class MVM : ViewModel() {

    val navigateEvents: MutableStateFlow<VmEvent?> = MutableStateFlow(null)
    val dialogs: MutableStateFlow<VmEvent?> = MutableStateFlow(null)
    val actions: MutableStateFlow<VmEvent?> = MutableStateFlow(null)

    protected fun emitLoading() = viewModelScope.launch {
        dialogs.emit(Loading(true))
    }

    protected fun dismissLoading() = viewModelScope.launch {
        dialogs.emit(Loading(false))
    }

    protected fun emitError(e: Throwable) = viewModelScope.launch {
        dialogs.emit(ShowError(e))
    }


    fun ViewModel.tryOnIO(block: suspend () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                block()
            } catch (e: Throwable) {
                emitError(e)
            }
        }
    }

}
