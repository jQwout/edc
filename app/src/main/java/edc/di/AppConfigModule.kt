package edc.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import edc.app.BuildConfig
import edc.app.R
import edc.common.di.CommonErrorText
import edc.common.di.HostUrl

@InstallIn(SingletonComponent::class)
@Module
object AppResourceModule {

    @Provides
    @HostUrl
    fun provideHostUrl(): String {
        return BuildConfig.SERVER_URL
    }

    @Provides
    @CommonErrorText
    fun provideCommonErrorText(@ApplicationContext context: Context): String {
        return context.resources.getString(R.string.common_error_text)
    }
}