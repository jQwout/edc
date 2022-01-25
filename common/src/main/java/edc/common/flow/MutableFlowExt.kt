package edc.common.flow

import kotlinx.coroutines.flow.MutableStateFlow

suspend fun <T : Any?> MutableStateFlow<T>.emitState(block: T.() -> T) {
    this.emit(block(this.value))
}


