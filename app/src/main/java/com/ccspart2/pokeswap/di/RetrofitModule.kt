package com.ccspart2.pokeswap.di

import com.ccspart2.pokeswap.data.localData.room.PokemonDatabase
import com.ccspart2.pokeswap.network.api.pokemon.PokemonApi
import com.ccspart2.pokeswap.network.api.pokemon.PokemonService
import com.ccspart2.pokeswap.network.common.Constants
import com.ccspart2.pokeswap.network.domain.PokemonUseCase
import com.ccspart2.pokeswap.network.repo.PokemonRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(ViewModelComponent::class)
object RetrofitModule {
    @Provides
    @ViewModelScoped
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.POKEMON_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    @Provides
    @ViewModelScoped
    fun providePokemonApi(retrofit: Retrofit): PokemonApi {
        return retrofit.create(PokemonApi::class.java)
    }

    @Provides
    @ViewModelScoped
    fun providePokemonService(pokemonApi: PokemonApi): PokemonService {
        return PokemonService(pokemonApi)
    }

    @Provides
    @ViewModelScoped
    fun providePokemonRepository(
        pokemonService: PokemonService,
        db: PokemonDatabase,
    ): PokemonRepository {
        return PokemonRepository(pokemonService, db)
    }

    @Provides
    @ViewModelScoped
    fun providePokemonUseCase(pokemonRepository: PokemonRepository): PokemonUseCase {
        return PokemonUseCase(pokemonRepository)
    }
}
