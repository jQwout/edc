package edc.domain.model.api

class ValutePair(
    val baseCode: String,
    val rate: Double,
    val destionationCode: String
) {
    fun reverse(): ValutePair {
        return ValutePair(baseCode = destionationCode, destionationCode = baseCode, rate = 1 / rate)
    }
}