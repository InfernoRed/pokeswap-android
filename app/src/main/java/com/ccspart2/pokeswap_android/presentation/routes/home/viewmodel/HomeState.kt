package com.ccspart2.pokeswap_android.presentation.routes.home.viewmodel

import com.ccspart2.pokeswap_android.network.domain.item.PokemonResponseItem

data class HomeState(
    val pokemonList: PokemonResponseItem? = null,
    val currentlyDisplayedImageUrl: String? = null,
    val pokemonCount: Int = 0,
)
