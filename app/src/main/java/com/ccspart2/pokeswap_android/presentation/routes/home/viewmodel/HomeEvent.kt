package com.ccspart2.pokeswap_android.presentation.routes.home.viewmodel

sealed class HomeEvent {

    data object RandomizeLeftCard : HomeEvent()
    data object RandomizeRightCard : HomeEvent()
}
