package edc.domain.di

import com.google.gson.FieldNamingPolicy
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import edc.common.gson.GsonUTCBuilder
import edc.domain.model.api.CbrDailyApi
import edc.domain.model.repo.LatestRepository
import edc.domain.model.repo.LatestRepositoryImpl
import edc.domain.model.repo.ValuteRepository
import edc.domain.model.repo.ValuteRepositoryImpl
import edc.network.di.NetworkComponentFactory

@InstallIn(SingletonComponent::class)
@Module
abstract class ValutesModule {

    @Binds
    abstract fun bindValutesRepository(impl: ValuteRepositoryImpl): ValuteRepository

    @Binds
    abstract fun bindLatestRepository(impl: LatestRepositoryImpl): LatestRepository


    companion object {

        @Provides
        fun provideApi(factory: NetworkComponentFactory): CbrDailyApi {
            val gson = GsonUTCBuilder.gsonBuilderWithUTC
                .setFieldNamingStrategy {
                    it.name.lowercase()
                }
                .create()
            return factory.createApi(
                gson,
                CbrDailyApi::class.java
            ) as CbrDailyApi
        }
    }
}