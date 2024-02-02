package com.ccspart2.pokeswap_android.presentation.routes.favorite.viewmodel

sealed class FavoriteEvent {

    data object RandomizeLeftCard : FavoriteEvent()
    data object RandomizeRightCard : FavoriteEvent()
}
