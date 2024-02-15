package com.ccspart2.pokeswap.presentation.routes.favorite.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.ccspart2.pokeswap.R
import com.ccspart2.pokeswap.ui.theme.PokeSwapAndroidTheme

@Composable
fun PokemonCardDisplay(
    pokemonCardName: String,
    imageResourceUrl: String? = null,
    flavorText: String = "",
    onImageClick: () -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .width(150.dp),
    ) {
        Text(
            text = pokemonCardName,
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center,
            maxLines = 2,
            overflow = TextOverflow.Clip,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
        )
        Image(
            painter = rememberImagePainter(
                data = imageResourceUrl ?: R.drawable.pokemon_card_backside,
                builder = {
                    placeholder(R.drawable.pokemon_card_backside)
                    error(R.drawable.pokemon_card_backside)
                },
            ),
            contentDescription = "Coil Image",
            modifier = Modifier
                .size(150.dp)
                .clickable {
                    onImageClick()
                },
        )
        Text(
            text = flavorText,
            style = MaterialTheme.typography.bodySmall,
            textAlign = TextAlign.Center,
            maxLines = 6,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .padding(
                    vertical = 10.dp,
                    horizontal = 10.dp,
                )
                .fillMaxWidth(),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PokemonCardDisplayPreview() {
    PokeSwapAndroidTheme {
        PokemonCardDisplay(
            pokemonCardName = "Pikachu with a large name",
            flavorText = "It spits fire that is hot enough to " +
                "melt boulders. It may cause forest fires by " +
                "blowing flames.",
            onImageClick = {},
        )
    }
}
