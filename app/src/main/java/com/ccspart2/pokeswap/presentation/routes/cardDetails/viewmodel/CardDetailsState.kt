package com.ccspart2.pokeswap.presentation.routes.cardDetails.viewmodel

import com.ccspart2.pokeswap.data.model.pokemonInfo.Pokemon

data class CardDetailsState(
    val selectedPokemon: Pokemon = Pokemon(),
    val isLoading: Boolean = true,
    val holoPrice: String = "$0.00",
    val reverseHoloPrice: String = "$0.00",
    val trendPrice: String = "$0.00",
    val avg30Price: String = "$0.00",
    val reverseHoloAvg30Price: String = "$0.00",
)
