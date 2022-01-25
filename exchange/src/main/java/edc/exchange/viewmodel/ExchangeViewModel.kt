package edc.exchange.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edc.common.lang.isNotNull
import edc.common.lang.required
import edc.domain.model.repo.LatestRepository
import edc.exchange.model.ExchangeScoreUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class ExchangeViewModel @Inject constructor(
    private val scoreUseCase: ExchangeScoreUseCase,
    private val latestRepository: LatestRepository,
) : ViewModel() {

    private val pairs = flow {
        while (true) {
            emit(latestRepository.getLatest())
            kotlinx.coroutines.delay(60 * 1000)
        }
    }
        .shareIn(viewModelScope, SharingStarted.Lazily, 1)

    private val inputState = MutableStateFlow<InputState?>(null)

    val baseValuteState = pairs.map { listOf(it.base) }.take(1)

    val otherValutesState = pairs.map { dto -> dto.pair.map { it.destionationCode } }.take(1)

    val scoreFlow = inputState
        .filterNotNull()
        .filter { it.ready() }
        .combine(pairs) { score, dto ->
            scoreUseCase.score(score.toScore(), dto)
        }
        .flowOn(Dispatchers.IO)

    fun emitBaseCode(code: String) {
        inputState.update {
            InputState(it?.main, code, it?.second, it?.secondCode)
        }
    }

    fun emitOtherCode(code: String) {
        inputState.update {
            InputState(it?.main, it?.mainCode, it?.second, code)
        }
    }

    fun emitValues(main: Double?, second: Double?) {
        inputState.update {
            InputState((main ?: 0.0), it?.mainCode, (second ?: 0.0), it?.secondCode)
        }
    }

    class InputState(
        val main: Double?,
        val mainCode: String?,
        val second: Double?,
        val secondCode: String?
    ) {
        fun ready(): Boolean {
            return main.isNotNull() && mainCode.isNotNull() && second.isNotNull() && secondCode.isNotNull()
        }

        fun toScore(): ExchangeScoreUseCase.Score {
            if (ready()) {
                return ExchangeScoreUseCase.Score(
                    mainCode.required(),
                    main.required(),
                    secondCode.required(),
                    second.required()
                )
            } else {
                throw IllegalStateException()
            }
        }
    }
}