package com.ccspart2.pokeswap.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ccspart2.pokeswap.presentation.routes.cameraAi.ui.CameraAIRoute
import com.ccspart2.pokeswap.presentation.routes.cardDetails.ui.CardDetailsRoute
import com.ccspart2.pokeswap.presentation.routes.favorite.ui.FavoriteRoute
import com.ccspart2.pokeswap.presentation.routes.home.ui.HomeRoute
import com.ccspart2.pokeswap.presentation.routes.lookup.ui.LookUpRoute
import com.ccspart2.pokeswap.presentation.routes.settings.ui.SettingsRoute

@Composable
fun PokemonSwapNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavigationItem.Home.route,
    ) {
        composable(route = NavigationItem.Home.route) {
            HomeRoute(navController = navController)
        }
        composable(route = NavigationItem.Favorite.route) {
            FavoriteRoute(navController = navController)
        }
        composable(route = NavigationItem.Lookup.route) {
            LookUpRoute(navController = navController)
        }
        composable(route = "${NavigationItem.CardDetails.route}/{${NavigationArguments.POKEMON_ID.key}}") {
            CardDetailsRoute(navController = navController)
        }
        composable(route = NavigationItem.Settings.route) {
            SettingsRoute(navController = navController)
        }
        composable(route = NavigationItem.CameraAi.route) {
            CameraAIRoute(navController = navController)
        }
    }
}
