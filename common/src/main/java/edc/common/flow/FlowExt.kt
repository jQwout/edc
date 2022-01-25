package edc.common.flow

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect

suspend fun <O : Any, T : O?> Flow<T>.collectNotNull(block: (O) -> Unit) {
    collect {
        if (it == null) return@collect

        block(it)
    }
}