package com.ccspart2.pokeswap.presentation.routes.lookup.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.ccspart2.pokeswap.R
import com.ccspart2.pokeswap.data.model.pokemonInfo.Pokemon
import com.ccspart2.pokeswap.ui.theme.PokeSwapAndroidTheme

@Composable
fun PokemonLazyColumnItem(
    pokemon: Pokemon,
    onClick: (String) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary)
            .clickable {
                onClick(pokemon.id)
            },
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Image(
                    painter = rememberImagePainter(
                        data = pokemon.images?.large ?: R.drawable.pokemon_card_backside,
                        builder = {
                            placeholder(R.drawable.pokemon_card_backside)
                            error(R.drawable.pokemon_card_backside)
                        },
                    ),
                    contentDescription = "Coil Image",
                    modifier = Modifier
                        .size(75.dp),
                )
                Text(
                    text = pokemon.name,
                    modifier = Modifier
                        .padding(16.dp),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )

                Icon(
                    painter = painterResource(
                        id = R.drawable.outline_arrow_circle_right_24,
                    ),
                    contentDescription = "",
                )
            }
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.outline),
                thickness = 1.dp,
            )
        }
    }
}

@Preview
@Composable
private fun PokemonLazyColumnItemPreview() {
    PokeSwapAndroidTheme {
        PokemonLazyColumnItem(
            pokemon = Pokemon(
                name = "Pikachu",
            ),
            onClick = {},
        )
    }
}
