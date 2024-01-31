package com.ccspart2.pokeswap_android.presentation.routes.home.viewmodel

import com.ccspart2.pokeswap_android.data.model.Pokemon

data class HomeState(
    val pokemonList: List<Pokemon> = listOf(),
    val currentlyDisplayedImageUrl: String? = null,
    val pokemonCount: Int = 0,
)
