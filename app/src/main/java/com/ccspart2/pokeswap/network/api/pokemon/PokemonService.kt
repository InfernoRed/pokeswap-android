package com.ccspart2.pokeswap.network.api.pokemon

import com.ccspart2.pokeswap.data.model.pokemonInfo.AllPokemonResponse
import com.ccspart2.pokeswap.data.model.pokemonInfo.PokemonResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PokemonService
@Inject
constructor(private val pokemonApi: PokemonApi) {
    suspend fun getAllPokemon(): AllPokemonResponse? {
        return withContext(Dispatchers.IO) {
            val pokemonList = pokemonApi.getALlPokemon()
            pokemonList.body()
        }
    }

    suspend fun getPokemonById(pokemonId: String): PokemonResponse? {
        return withContext(Dispatchers.IO) {
            val pokemon = pokemonApi.getPokemonById(pokemonId)
            pokemon.body()
        }
    }
}
