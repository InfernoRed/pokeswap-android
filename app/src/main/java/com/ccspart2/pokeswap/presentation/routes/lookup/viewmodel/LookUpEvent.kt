package com.ccspart2.pokeswap.presentation.routes.lookup.viewmodel

sealed class LookUpEvent {

    class OnSearchTextChange(val text: String) : LookUpEvent()
}
