package com.ccspart2.pokeswap.presentation.routes.home.viewmodel

import com.ccspart2.pokeswap.data.model.pokemonInfo.Pokemon

data class HomeState(
    val favoritePokemonId: String = "",
    val isCurrencyLoading: Boolean = true,
    val isPokemonLoading: Boolean = true,
    val favoritePokemon: Pokemon = Pokemon(),
)
