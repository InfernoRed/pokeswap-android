package com.ccspart2.pokeswap_android.presentation.routes.lookup.viewmodel

sealed class LookUpEvent {

    class OnSearchTextChange(val text: String) : LookUpEvent()
}
