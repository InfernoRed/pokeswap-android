package com.ccspart2.pokeswap_android.presentation.routes.home.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ccspart2.pokeswap_android.R
import com.ccspart2.pokeswap_android.ui.theme.PokeSwapAndroidTheme
import com.ccspart2.pokeswap_android.ui.theme.pokedex_len_color

@Composable
fun PokedexTopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary),
    ) {
        Canvas(
            modifier = Modifier
                .padding(10.dp)
                .size(80.dp),
            onDraw = {
                drawCircle(
                    color = Color.White,
                )
                drawCircle(
                    color = pokedex_len_color,
                    radius = 99f,
                )
            },
        )

        Canvas(
            modifier = Modifier
                .padding(
                    start = 10.dp,
                    top = 10.dp,
                )
                .size(30.dp),
            onDraw = {
                drawCircle(
                    color = Color.Black,
                )
                drawCircle(
                    color = Color.Red,
                    radius = 30f,
                )
            },
        )
        Canvas(
            modifier = Modifier
                .padding(10.dp)
                .size(30.dp),
            onDraw = {
                drawCircle(
                    color = Color.Black,
                )
                drawCircle(
                    color = Color.Yellow,
                    radius = 30f,
                )
            },
        )
        Canvas(
            modifier = Modifier
                .padding(
                    top = 10.dp,
                )
                .size(30.dp),
            onDraw = {
                drawCircle(
                    color = Color.Black,
                )
                drawCircle(
                    color = Color.Green,
                    radius = 30f,
                )
            },
        )

        Spacer(modifier = Modifier.weight(1f))

        Image(
            painter = painterResource(id = R.drawable.irt_dragon_vector),
            contentDescription = "",
            modifier = Modifier.size(120.dp),
        )
    }
}

@Preview
@Composable
private fun PokedexTopBarPreview() {
    PokeSwapAndroidTheme {
        PokedexTopBar()
    }
}
