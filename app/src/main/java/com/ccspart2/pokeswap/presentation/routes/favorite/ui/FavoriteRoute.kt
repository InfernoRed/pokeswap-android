package com.ccspart2.pokeswap.presentation.routes.favorite.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ccspart2.pokeswap.R
import com.ccspart2.pokeswap.presentation.core.ui.PreviewScreen
import com.ccspart2.pokeswap.presentation.core.ui.components.FilledButton
import com.ccspart2.pokeswap.presentation.core.ui.components.LoadingDialog
import com.ccspart2.pokeswap.presentation.core.ui.components.PokedexTopBar
import com.ccspart2.pokeswap.presentation.navigation.NavigationItem
import com.ccspart2.pokeswap.presentation.routes.favorite.ui.components.ChosenCardDialog
import com.ccspart2.pokeswap.presentation.routes.favorite.ui.components.PokemonCardDisplay
import com.ccspart2.pokeswap.presentation.routes.favorite.viewmodel.FavoriteEvent
import com.ccspart2.pokeswap.presentation.routes.favorite.viewmodel.FavoriteState
import com.ccspart2.pokeswap.presentation.routes.favorite.viewmodel.FavoriteViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun FavoriteRoute(
    navController: NavController,
) {
    val viewModel: FavoriteViewModel = hiltViewModel()
    FavoriteScreen(
        viewModelState = viewModel.viewState,
        onLeftCardButtonClick = {
            viewModel.handleEvent(FavoriteEvent.RandomizeRightCard)
        },
        onRightButtonClick = {
            viewModel.handleEvent(FavoriteEvent.RandomizeLeftCard)
        },
        onFavoriteCardDialogClose = {
            navController.navigate(NavigationItem.Home.route)
        },
    )
}

@Composable
private fun FavoriteScreen(
    viewModelState: StateFlow<FavoriteState>,
    onLeftCardButtonClick: () -> Unit,
    onRightButtonClick: () -> Unit,
    onFavoriteCardDialogClose: () -> Unit,
) {
    val viewState by viewModelState.collectAsState()

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

        if (viewState.isLoading) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                LoadingDialog()
            }
        } else if (viewState.favPokemonSelected) {
            viewState.favPokemon?.let { selectedFavoritePokemon ->
                ChosenCardDialog(
                    pokemonCardName = selectedFavoritePokemon.name,
                    imageResourceUrl = selectedFavoritePokemon.images?.large,
                    rarity = selectedFavoritePokemon.rarity,
                    avgMarketPrice = selectedFavoritePokemon.cardmarket.prices.averageSellPrice,
                    onClose = onFavoriteCardDialogClose,
                )
            }
        } else {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(20.dp)
                    .weight(1f)
                    .clip(shape = RoundedCornerShape(10.dp))
                    .background(Color.White),
            ) {
                Text(
                    text = stringResource(id = R.string.favorite_title),
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(
                            top = 35.dp,
                            start = 25.dp,
                            end = 25.dp,
                        ),
                )
                Spacer(modifier = Modifier.height(30.dp))

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth(),
                ) {
                    PokemonCardDisplay(
                        pokemonCardName = viewState.leftDisplayedPokemon.name,
                        imageResourceUrl = viewState.leftDisplayedPokemon.images?.large,
                        flavorText = viewState.leftDisplayedPokemon.flavorText,
                        onImageClick = onLeftCardButtonClick,
                    )

                    Image(
                        painter = painterResource(id = R.drawable.versus_logo),
                        contentDescription = "",
                        modifier = Modifier
                            .padding(top = 100.dp)
                            .size(50.dp),
                    )

                    PokemonCardDisplay(
                        pokemonCardName = viewState.rightDisplayedPokemon.name,
                        imageResourceUrl = viewState.rightDisplayedPokemon.images?.large,
                        flavorText = viewState.rightDisplayedPokemon.flavorText,
                        onImageClick = onRightButtonClick,
                    )
                }
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement
                    .spacedBy(
                        space = 40.dp,
                        alignment = Alignment.CenterHorizontally,
                    ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
            ) {
                FilledButton(
                    onClick = { onLeftCardButtonClick() },
                ) {
                    Text(
                        text = stringResource(id = R.string.favorite_left_button_label),
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier
                            .padding(horizontal = 40.dp),
                    )
                }
                FilledButton(
                    onClick = { onRightButtonClick() },
                ) {
                    Text(
                        text = stringResource(id = R.string.favorite_right_button_label),
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier
                            .padding(horizontal = 40.dp),
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun FavoriteScreenPreview() {
    PreviewScreen {
        FavoriteScreen(
            onRightButtonClick = {},
            onLeftCardButtonClick = {},
            onFavoriteCardDialogClose = {},
            viewModelState =
            MutableStateFlow(
                FavoriteState(
                    isLoading = false,
                ),
            ),
        )
    }
}
