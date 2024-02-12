package com.ccspart2.pokeswap_android.presentation.routes.cardDetails.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ccspart2.pokeswap_android.presentation.core.ui.PreviewScreen
import com.ccspart2.pokeswap_android.presentation.routes.cardDetails.viewmodel.CardDetailsViewModel

@Composable
fun CardDetailsRoute(
    navController: NavController,
) {
    val viewModel: CardDetailsViewModel = hiltViewModel()
    CardDetailsScreen()
}

@Composable
private fun CardDetailsScreen() {
}

@Preview
@Composable
private fun CardDetailsScreenPreview() {
    PreviewScreen {
        CardDetailsScreen()
    }
}
