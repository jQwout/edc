package edc.network.di

import android.content.Context
import androidx.fragment.app.Fragment
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import edc.common.di.CommonErrorText
import edc.common.di.HostUrl
import edc.network.interceptor.ErrorInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

@InstallIn(SingletonComponent::class)
@Module
object NetworkHiltModule {

    @Provides
    fun provideNetworkComponentFactory(
        @ApplicationContext context: Context,
        @HostUrl host: String,
        @CommonErrorText commonErrorText: String
    ): NetworkComponentFactory {

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return NetworkComponentFactory(
            host,
            context,
            ErrorInterceptor(commonErrorText),
            loggingInterceptor
        )
    }
}
