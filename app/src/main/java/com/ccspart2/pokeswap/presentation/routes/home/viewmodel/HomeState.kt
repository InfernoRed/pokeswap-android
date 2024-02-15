package com.ccspart2.pokeswap.presentation.routes.home.viewmodel

import com.ccspart2.pokeswap.data.model.pokemonInfo.Pokemon

data class HomeState(
    val favoritePokemonId: String = "",
    val isLoading: Boolean = true,
    val favoritePokemon: Pokemon = Pokemon()
)
