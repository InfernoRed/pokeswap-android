package com.ccspart2.pokeswap_android.network.domain

import com.ccspart2.pokeswap_android.network.domain.item.PokemonResponseItem
import com.ccspart2.pokeswap_android.network.repo.PokemonRepository
import javax.inject.Inject

class GetAllPokemonUseCase @Inject
constructor(private val pokemonRepository: PokemonRepository) {
    suspend operator fun invoke(): PokemonResponseItem? {
        return pokemonRepository.getAllPokemon()
    }
}
