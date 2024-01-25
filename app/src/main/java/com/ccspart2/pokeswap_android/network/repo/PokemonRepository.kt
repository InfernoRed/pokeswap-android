package com.ccspart2.pokeswap_android.network.repo

import com.ccspart2.pokeswap_android.network.data.PokemonService
import com.ccspart2.pokeswap_android.network.domain.item.PokemonResponseItem
import com.ccspart2.pokeswap_android.network.domain.item.toPokemonResponseItem
import javax.inject.Inject

class PokemonRepository @Inject
constructor(
    private val pokemonService: PokemonService,
) {
    suspend fun getAllPokemon(): PokemonResponseItem? {
        return pokemonService.getAllPokemon()?.toPokemonResponseItem()
    }
}
