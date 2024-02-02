package com.ccspart2.pokeswap_android.presentation.routes.favorite.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import coil.compose.rememberImagePainter
import com.ccspart2.pokeswap_android.R
import com.ccspart2.pokeswap_android.presentation.core.ui.PreviewScreen
import com.ccspart2.pokeswap_android.presentation.core.ui.components.FilledButton

@Composable
fun ChosenCardDialog(
    pokemonCardName: String = "",
    imageResourceUrl: String? = null,
    rarity: String? = null,
    avgMarketPrice: Double = 0.0,
    onClose: () -> Unit,
) {
    Dialog(onDismissRequest = onClose) {
        Surface(
            shape = RoundedCornerShape(16.dp),
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(470.dp)
                    .background(MaterialTheme.colorScheme.secondary),
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxSize(),
                ) {
                    Text(
                        text = "Your Favorite Pokemon is:",
                        style = MaterialTheme.typography.titleMedium,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(vertical = 15.dp),
                    )

                    Text(
                        text = pokemonCardName,
                        style = MaterialTheme.typography.titleLarge,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(vertical = 10.dp),
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
                            .size(200.dp),
                    )
                    Text(
                        text = "Rarity: $rarity",
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(vertical = 10.dp),
                    )
                    Text(
                        text = "Market Price: $avgMarketPrice",
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(vertical = 10.dp),
                    )

                    FilledButton(
                        onClick = onClose,
                    ) {
                        Text(
                            text = "Close",
                            modifier = Modifier
                                .padding(horizontal = 20.dp),
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun ChosenCardDialogPreview() {
    PreviewScreen {
        ChosenCardDialog(
            pokemonCardName = "Charizard",
            rarity = "Holo",
            onClose = {},
        )
    }
}
