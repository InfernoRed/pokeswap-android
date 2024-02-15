package com.ccspart2.pokeswap_android.network.domain

import com.ccspart2.pokeswap_android.data.model.pokemonInfo.Pokemon
import com.ccspart2.pokeswap_android.network.repo.PokemonRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PokemonUseCase @Inject
constructor(private val pokemonRepository: PokemonRepository) {
    suspend operator fun invoke(): Flow<MutableList<Pokemon>> {
        return pokemonRepository.getAllPokemonData()
    }

    suspend fun findPokemonById(pokemonId: String): Flow<Pokemon> {
        return pokemonRepository.getPokemonById(pokemonId)
    }
}
