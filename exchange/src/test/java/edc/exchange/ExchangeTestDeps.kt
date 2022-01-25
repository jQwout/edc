package edc.exchange

import edc.domain.model.api.ValutePair
import edc.domain.model.repo.LatestDto
import edc.domain.model.repo.LatestRepository
import edc.exchange.model.ExchangeScoreUseCase

object ExchangeTestDeps {

    private val latestTemplateRaw = LatestDto(
        "RUB", listOf(
            ValutePair("RUB", 0.013039f, "USD")
        )
    )

    val latestRepository = object : LatestRepository {
        override suspend fun getLatest(): LatestDto {
            return latestTemplateRaw
        }
    }

    val exchangeScoreUseCase = ExchangeScoreUseCase(latestRepository)
}