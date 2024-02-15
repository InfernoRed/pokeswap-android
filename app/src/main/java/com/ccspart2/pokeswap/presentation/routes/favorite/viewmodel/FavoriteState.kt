package com.ccspart2.pokeswap.presentation.routes.favorite.viewmodel

import com.ccspart2.pokeswap.data.model.pokemonInfo.Pokemon

data class FavoriteState(
    val pokemonList: List<Pokemon> = listOf(),
    val leftDisplayedPokemon: Pokemon = Pokemon(),
    val rightDisplayedPokemon: Pokemon = Pokemon(),
    val favPokemon: Pokemon? = null,
    val pokemonCount: Int = 0,
    val isLoading: Boolean = true,
    val favPokemonSelected: Boolean = false,
)
