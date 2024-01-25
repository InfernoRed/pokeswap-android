package com.ccspart2.pokeswap_android.presentation.routes.home

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.ccspart2.pokeswap_android.presentation.core.ui.PreviewScreen

@Composable
fun HomeRoute(
    navController: NavController,
    // Add arguments
) {
    HomeScreen()
}

@Composable
private fun HomeScreen() {
    Column {
        Text(text = "Charlie Castro")
    }
}

@Preview(heightDp = 720, widthDp = 360)
@Composable
private fun HomePreview() {
    PreviewScreen {
        HomeScreen()
    }
}
