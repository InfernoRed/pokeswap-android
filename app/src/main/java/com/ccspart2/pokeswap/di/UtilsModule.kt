package com.ccspart2.pokeswap.di

import com.ccspart2.pokeswap.network.repo.CurrencyExchangeRatesRepository
import com.ccspart2.pokeswap.utils.CurrencyUtils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UtilsModule {
    @Singleton
    @Provides
    fun provideCurrencyUtils(repository: CurrencyExchangeRatesRepository): CurrencyUtils {
        return CurrencyUtils(repository)
    }
}
