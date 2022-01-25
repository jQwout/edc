package edc.exchange.model

import edc.domain.model.repo.LatestDto
import edc.domain.model.repo.LatestRepository
import kotlinx.coroutines.flow.*
import java.math.RoundingMode
import javax.inject.Inject

class ExchangeScoreUseCase @Inject constructor() {

    fun score(value: Score, latest: LatestDto): Score {
        return if (value.mainValue > 0f) {
            val rate = latest.pair.first { it.destionationCode == value.secondCode }.rate
            val ex = value.mainValue * rate
            Score(
                value.mainCode,
                value.mainValue,
                value.secondCode,
                ex.toBigDecimal().setScale(5, RoundingMode.FLOOR).toDouble()
            )
        } else {
            val pair = latest.pair.first { it.destionationCode == value.secondCode }
            val ex = pair.reverse().rate * value.secondValue
            Score(
                value.mainCode,
                ex.toBigDecimal().setScale(5, RoundingMode.FLOOR).toDouble(),
                value.secondCode,
                value.secondValue
            )
        }
    }

    class Score(
        val mainCode: String,
        val mainValue: Double,
        val secondCode: String,
        val secondValue: Double
    )
}