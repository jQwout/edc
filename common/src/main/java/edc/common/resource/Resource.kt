package edc.common.resource

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

sealed class Resource<T> {
    object NotYet : Resource<Nothing>()
    object Loading : Resource<Nothing>()
    class Error(val error: Throwable) : Resource<Nothing>()
    class Data<T>(val data: T) : Resource<T>()

    val isLoading get() = this is Loading
    val isError get() = this is Error
    val isSuccess get() = this is Data

    fun <U> getDataOrNull() = if (isSuccess) (this as Data).data else null

    fun <U> mapResource(mapper: (T) -> U): Resource<out U> {

        return when (this) {
            is Error -> {
                Error(error)
            }
            is Loading -> {
                Loading
            }
            is Data -> {
                data(mapper(data))
            }
            is NotYet -> {
                NotYet
            }
        }

    }

    companion object {
        fun <T> data(data: T) = Data(data)
        fun <T> error(e: Throwable) = Error(e)
        fun <T> load() = Loading
        fun <T> notYet() = NotYet


        fun <T> wrap(block: suspend () -> T): Flow<Resource<out T>> {
            return flow {
                emit(load<T>())
                try {
                    emit(data(block()))
                } catch (e: Throwable) {
                    emit(error<T>(e))
                }
            }
        }
    }
}
