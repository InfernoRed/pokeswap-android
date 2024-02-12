package com.ccspart2.pokeswap_android.presentation.routes.cardDetails.viewmodel

import com.ccspart2.pokeswap_android.data.model.Pokemon

data class CardDetailsState(
    val selectedPokemon: Pokemon = Pokemon(),
    val isLoading: Boolean = true,
)
