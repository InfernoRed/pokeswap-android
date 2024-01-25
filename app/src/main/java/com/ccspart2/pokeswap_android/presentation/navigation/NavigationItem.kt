package com.ccspart2.pokeswap_android.presentation.navigation

sealed class NavigationItem(val route: String) {
    data object Home : NavigationItem(Screen.HOME.name)
}

enum class Screen {
    HOME,
}
