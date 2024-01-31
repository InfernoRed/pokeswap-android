package com.ccspart2.pokeswap_android.network.domain

import com.ccspart2.pokeswap_android.data.model.Pokemon
import com.ccspart2.pokeswap_android.network.domain.item.PokemonResponseItem
import com.ccspart2.pokeswap_android.network.repo.PokemonRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllPokemonUseCase @Inject
constructor(private val pokemonRepository: PokemonRepository) {
    suspend operator fun invoke(): PokemonResponseItem? {
        return pokemonRepository.getAllPokemonData()
    }
    fun getLocalPokemonData(): Flow<MutableList<Pokemon>> {
        return pokemonRepository.getAllLocalPokemonData()
    }
}
