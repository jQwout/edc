package edc.domain.model.repo

import edc.domain.model.api.CbrDailyApi
import edc.domain.model.api.ValutePair
import javax.inject.Inject

interface LatestRepository {

    suspend fun getLatest(): LatestDto
}

class LatestRepositoryImpl @Inject constructor(private val api: CbrDailyApi) : LatestRepository {
    override suspend fun getLatest(): LatestDto {
        val data = api.getLatest()
        return LatestDto(
            data.base,
            data.rates.map { (k, v) ->
                ValutePair(data.base, v, k)
            }
        )
    }
}

class LatestDto(
    val base: String,
    val pair: List<ValutePair>
)