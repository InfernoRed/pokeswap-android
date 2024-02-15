package com.ccspart2.pokeswap.network.api.pokemon

import com.ccspart2.pokeswap.data.model.pokemonInfo.AllPokemonResponse
import com.ccspart2.pokeswap.data.model.pokemonInfo.PokemonResponse
import com.ccspart2.pokeswap.network.common.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonApi {
    @GET(Constants.POKEMON_ENDPOINT)
    suspend fun getALlPokemon(): Response<AllPokemonResponse>

    @GET("${Constants.POKEMON_ENDPOINT}{pokemonId}")
    suspend fun getPokemonById(@Path("pokemonId") pokemonId: String): Response<PokemonResponse>
}
