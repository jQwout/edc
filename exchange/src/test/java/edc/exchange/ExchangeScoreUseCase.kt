package edc.exchange

import edc.exchange.model.ExchangeScoreUseCase
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.cancellable
import org.junit.Test

class ExchangeScoreUseCaseTest {

    @Test
    fun test() = runBlocking {

        ExchangeTestDeps.exchangeScoreUseCase.emitScore(
            ExchangeScoreUseCase.ScoreValue(
                "RUB",
                100f,
                "USD"
            )
        )

        try {
            ExchangeTestDeps.exchangeScoreUseCase.result
                .cancellable()
                .collect {
                    println(
                        "${it.code} - ${it.value} \n${it.toCode} - ${it.exchange}"
                    )
                    currentCoroutineContext().cancel()

                }
        } catch (j: CancellationException) {

        }
    }

    @Test
    fun test2() = runBlocking {

        ExchangeTestDeps.exchangeScoreUseCase.emitScore(
            ExchangeScoreUseCase.ScoreValue(
                "USD",
                100f,
                "RUB"
            )
        )

        try {
            ExchangeTestDeps.exchangeScoreUseCase.result
                .cancellable()
                .collect {
                    println(
                        "${it.code} - ${it.value} \n${it.toCode} - ${it.exchange}"
                    )
                    currentCoroutineContext().cancel()

                }
        } catch (j: CancellationException) {

        }
    }
}