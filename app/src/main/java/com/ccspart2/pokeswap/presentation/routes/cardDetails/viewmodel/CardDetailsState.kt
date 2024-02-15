package com.ccspart2.pokeswap.presentation.routes.cardDetails.viewmodel

import com.ccspart2.pokeswap.data.model.pokemonInfo.Pokemon

data class CardDetailsState(
    val selectedPokemon: Pokemon = Pokemon(),
    val isLoading: Boolean = true,
)
