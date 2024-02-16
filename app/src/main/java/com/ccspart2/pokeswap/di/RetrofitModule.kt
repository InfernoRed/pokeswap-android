package com.ccspart2.pokeswap.di

import com.ccspart2.pokeswap.data.localData.room.PokemonDatabase
import com.ccspart2.pokeswap.network.api.currency.CurrencyExchangeRatesApi
import com.ccspart2.pokeswap.network.api.currency.CurrencyExchangeRatesServices
import com.ccspart2.pokeswap.network.api.pokemon.PokemonApi
import com.ccspart2.pokeswap.network.api.pokemon.PokemonService
import com.ccspart2.pokeswap.network.common.Constants
import com.ccspart2.pokeswap.network.domain.CurrencyExchangeUseCase
import com.ccspart2.pokeswap.network.domain.PokemonUseCase
import com.ccspart2.pokeswap.network.repo.CurrencyExchangeRatesRepository
import com.ccspart2.pokeswap.network.repo.PokemonRepository
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {
    @Provides
    @Singleton
    fun providesMoshi(): Moshi {
        return Moshi.Builder().build()
    }

    @Provides
    @Singleton
    @Named("Pokemon")
    fun provideRetrofit(moshi: Moshi): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.POKEMON_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Provides
    @Singleton
    @Named("Currency")
    fun provideCurrencyRetrofit(moshi: Moshi): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.EXCHANGE_RATE_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Provides
    @Singleton
    fun providePokemonApi(@Named("Pokemon") retrofit: Retrofit): PokemonApi {
        return retrofit.create(PokemonApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCurrencyApi(@Named("Currency") retrofit: Retrofit): CurrencyExchangeRatesApi {
        return retrofit.create(CurrencyExchangeRatesApi::class.java)
    }

    @Provides
    @Singleton
    fun providePokemonService(pokemonApi: PokemonApi): PokemonService {
        return PokemonService(pokemonApi)
    }

    @Provides
    @Singleton
    fun provideCurrencyService(currencyExchangeRatesApi: CurrencyExchangeRatesApi): CurrencyExchangeRatesServices {
        return CurrencyExchangeRatesServices(currencyExchangeRatesApi)
    }

    @Provides
    @Singleton
    fun providePokemonRepository(
        pokemonService: PokemonService,
        db: PokemonDatabase,
    ): PokemonRepository {
        return PokemonRepository(pokemonService, db)
    }

    @Provides
    @Singleton
    fun provideCurrencyRepository(
        currencyExchangeRatesServices: CurrencyExchangeRatesServices,
        db: PokemonDatabase,
    ): CurrencyExchangeRatesRepository {
        return CurrencyExchangeRatesRepository(currencyExchangeRatesServices, db)
    }

    @Provides
    @Singleton
    fun providePokemonUseCase(pokemonRepository: PokemonRepository): PokemonUseCase {
        return PokemonUseCase(pokemonRepository)
    }

    @Provides
    @Singleton
    fun provideCurrencyUseCase(currencyExchangeRatesRepository: CurrencyExchangeRatesRepository): CurrencyExchangeUseCase {
        return CurrencyExchangeUseCase(currencyExchangeRatesRepository)
    }
}
