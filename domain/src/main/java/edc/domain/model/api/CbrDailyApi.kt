package edc.domain.model.api

import edc.common.network.http.header.Headers
import retrofit2.http.GET
import retrofit2.http.Header

interface CbrDailyApi {

    @GET("latest.js")
    suspend fun getLatest(): LatestPayload

    @GET("daily_json.js")
    suspend fun getDaily(@Header(Headers.CACHE_HEADER) header: String): DailyPayload
}