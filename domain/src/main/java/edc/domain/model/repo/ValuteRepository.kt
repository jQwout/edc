package edc.domain.model.repo

import edc.common.lang.Sort
import edc.common.network.http.header.Headers
import edc.domain.model.api.CbrDailyApi
import edc.domain.model.api.Valute
import javax.inject.Inject

interface ValuteRepository {
    suspend fun getValutes(sort: Sort<Valute>? = null): List<Valute>
}

class ValuteRepositoryImpl @Inject constructor(
    private val api: CbrDailyApi
) : ValuteRepository {

    override suspend fun getValutes(sort: Sort<Valute>?): List<Valute> {
        val sort = when {
            sort == null -> {
                null
            }
            sort.ordering == Sort.Ordering.ASC -> { list: List<Valute> ->
                list.sortedBy(sort.keySelector as (Valute) -> Comparable<Comparable<*>>)
            }
            else -> { list: List<Valute> ->
                list.sortedByDescending(sort.keySelector as (Valute) -> Comparable<Comparable<*>>)
            }
        }
        val list = api.getDaily(header = Headers.CACHE_ONE_MIN).valute.values.toList()
        return sort?.invoke(list) ?: list
    }
}