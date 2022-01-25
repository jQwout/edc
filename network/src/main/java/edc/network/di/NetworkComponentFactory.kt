package edc.network.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.hilt.android.qualifiers.ApplicationContext
import edc.common.di.HostUrl
import edc.network.interceptor.ErrorInterceptor
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkComponentFactory @Inject constructor(
    @HostUrl private val url: String,
    @ApplicationContext private val context: Context,
    private val errorInterceptor: ErrorInterceptor,
    private val loggingInterceptor: HttpLoggingInterceptor
) {

    private fun createOkhttpClient(): OkHttpClient {
        val protocols = arrayListOf(Protocol.HTTP_1_1, Protocol.HTTP_2) // Support h2

        val cacheSize = 2L * 1024 * 1024 // 2 MiB
        val cacheDir = context.getDir("conference_data", Context.MODE_PRIVATE)
        val cache = Cache(cacheDir, cacheSize)

        return OkHttpClient.Builder()
            .protocols(protocols)
            .cache(cache)
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
            .callTimeout(TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .addInterceptor(errorInterceptor)
            .build()
    }

    private fun createRetrofit(okHttpClient: OkHttpClient, gson: Gson = Gson()): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(url)
            .client(okHttpClient)
            .build()
    }

    fun createApi(gson: Gson, apiClass: Class<*>): Any {
        return createRetrofit(createOkhttpClient(), gson)
            .create(apiClass)
    }

    companion object {
        const val TIMEOUT = 10L
    }
}