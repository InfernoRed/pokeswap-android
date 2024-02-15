package com.ccspart2.pokeswap.presentation.routes.favorite.viewmodel

sealed class FavoriteEvent {

    data object RandomizeLeftCard : FavoriteEvent()
    data object RandomizeRightCard : FavoriteEvent()
}
