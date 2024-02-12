package com.ccspart2.pokeswap_android.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ccspart2.pokeswap_android.presentation.routes.cardDetails.ui.CardDetailsRoute
import com.ccspart2.pokeswap_android.presentation.routes.favorite.ui.FavoriteRoute
import com.ccspart2.pokeswap_android.presentation.routes.home.ui.HomeRoute
import com.ccspart2.pokeswap_android.presentation.routes.lookup.ui.LookUpRoute

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
    }
}
