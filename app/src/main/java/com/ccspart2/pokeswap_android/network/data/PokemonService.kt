package com.ccspart2.pokeswap_android.network.data

import com.ccspart2.pokeswap_android.data.model.PokemonResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PokemonService
@Inject
constructor(private val pokemonApi: PokemonApi) {
    suspend fun getAllPokemon(): PokemonResponse? {
        return withContext(Dispatchers.IO) {
            val pokemon = pokemonApi.getALlPokemon()
            pokemon.body()
        }
    }
}
