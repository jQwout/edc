package edc.common.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

open class InAppRepository<T>(initial: T? = null) {

    protected var inMemoryData: MutableStateFlow<T?> = MutableStateFlow(initial)

    suspend fun set(data: T) {
        inMemoryData.emit(data)
    }

    fun get(): Flow<T?> = inMemoryData

    fun getLastValue(): T? = inMemoryData.value
}

abstract class DataHolder<T>(initial: T? = null, val dataProvider: suspend () -> T) {

    protected var data: MutableStateFlow<T?> = MutableStateFlow(initial)

    suspend fun get(): T {
        val last = data.value
        return last ?: refresh()
    }

    suspend fun refresh(): T {
        val d = dataProvider()
        data.emit(d)
        return d
    }
}