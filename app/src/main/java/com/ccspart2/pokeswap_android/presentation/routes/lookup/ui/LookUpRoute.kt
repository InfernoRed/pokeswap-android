package com.ccspart2.pokeswap_android.presentation.routes.lookup.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.ccspart2.pokeswap_android.ui.theme.PokeSwapAndroidTheme

@Composable
fun LookUpRoute(navController: NavController) {
    LookupScreen()
}

@Composable
private fun LookupScreen() {
}

@Preview
@Composable
private fun LookupScreenPreview() {
    PokeSwapAndroidTheme {
        LookupScreen()
    }
}
