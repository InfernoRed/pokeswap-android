package com.ccspart2.pokeswap.presentation.navigation

sealed class NavigationItem(val route: String) {
    data object Favorite : NavigationItem(Screen.FAVORITE.name)
    data object Home : NavigationItem(Screen.HOME.name)
    data object Lookup : NavigationItem(Screen.LOOKUP.name)
    data object CardDetails : NavigationItem(Screen.CARD_DETAILS.name)
    data object Settings : NavigationItem(Screen.SETTINGS.name)
    data object CameraAi : NavigationItem(Screen.CAMERA.name)
}

enum class Screen {
    HOME,
    FAVORITE,
    LOOKUP,
    CARD_DETAILS,
    SETTINGS,
    CAMERA,
}

enum class NavigationArguments(val key: String) {
    POKEMON_ID(key = "pokemonId"),
}
