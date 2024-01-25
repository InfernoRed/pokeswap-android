package com.ccspart2.pokeswap_android.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ccspart2.pokeswap_android.presentation.routes.home.HomeRoute

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
    }
}
