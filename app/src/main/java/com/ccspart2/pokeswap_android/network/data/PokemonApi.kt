package com.ccspart2.pokeswap_android.network.data

import com.ccspart2.pokeswap_android.data.model.PokemonResponse
import com.ccspart2.pokeswap_android.network.common.Constants
import retrofit2.Response
import retrofit2.http.GET

interface PokemonApi {
    @GET(Constants.POKEMON_ENDPOINT)
    suspend fun getALlPokemon(): Response<PokemonResponse>
}
