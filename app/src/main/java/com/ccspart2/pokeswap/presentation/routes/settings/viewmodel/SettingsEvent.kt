package com.ccspart2.pokeswap.presentation.routes.settings.viewmodel

sealed class SettingsEvent {
    class ToggleCurrency(val selectEuro: Boolean) : SettingsEvent()
}
