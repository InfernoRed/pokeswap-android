package com.ccspart2.pokeswap_android.presentation.routes.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ccspart2.pokeswap_android.presentation.core.ui.PreviewScreen
import com.ccspart2.pokeswap_android.presentation.routes.home.components.PokedexTopBar

@Composable
fun HomeRoute() {
    HomeScreen()
}

@Composable
private fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary),
    ) {
        PokedexTopBar()
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.outlineVariant),
            thickness = 1.dp,
        )

        Column(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxSize()
                .clip(shape = RoundedCornerShape(10.dp))
                .background(Color.White),
        ) {}
    }
}

@Preview
@Composable
private fun HomePreview() {
    PreviewScreen {
        HomeScreen()
    }
}
