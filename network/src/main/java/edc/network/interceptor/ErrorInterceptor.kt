package edc.network.interceptor

import edc.common.di.CommonErrorText
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class ErrorInterceptor(
    @CommonErrorText
    private val commonErrorText: String
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
        val response = chain.proceed(request)

        if (response.code >= 500) {
            throw BadRequestException(response.code.toString(), commonErrorText)
        }

        if (response.code >= 400) {
            throw BadRequestException(response.code.toString(), commonErrorText)
        }

        return response
    }
}

class BadRequestException(val code: String, val errorText: String) : Exception(errorText)