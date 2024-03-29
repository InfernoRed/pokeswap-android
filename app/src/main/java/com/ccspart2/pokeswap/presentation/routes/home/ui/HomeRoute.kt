package com.ccspart2.pokeswap.presentation.routes.home.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.ccspart2.pokeswap.R
import com.ccspart2.pokeswap.data.model.pokemonInfo.Pokemon
import com.ccspart2.pokeswap.presentation.core.ui.PreviewScreen
import com.ccspart2.pokeswap.presentation.core.ui.components.FilledButton
import com.ccspart2.pokeswap.presentation.core.ui.components.LoadingDialog
import com.ccspart2.pokeswap.presentation.navigation.NavigationItem
import com.ccspart2.pokeswap.presentation.routes.home.viewmodel.HomeState
import com.ccspart2.pokeswap.presentation.routes.home.viewmodel.HomeViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun HomeRoute(
    navController: NavController,
) {
    val viewModel: HomeViewModel = hiltViewModel()
    HomeScreen(
        viewModelState = viewModel.viewState,
        onCardLookupButtonClick = {
            navController.navigate(NavigationItem.Lookup.route)
        },
        onFavoriteButtonClick = {
            navController.navigate(NavigationItem.Favorite.route)
        },
        onSettingsButtonClick = {
            navController.navigate(NavigationItem.Settings.route)
        },
        onCameraButtonClick = {
            navController.navigate(NavigationItem.CameraAi.route)
        },
    )
}

@Composable
private fun HomeScreen(
    viewModelState: StateFlow<HomeState>,
    onCardLookupButtonClick: () -> Unit,
    onFavoriteButtonClick: () -> Unit,
    onSettingsButtonClick: () -> Unit,
    onCameraButtonClick: () -> Unit,
) {
    val viewState by viewModelState.collectAsState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary),
    ) {
        if (viewState.isPokemonLoading || viewState.isCurrencyLoading) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                LoadingDialog()
            }
        } else {
            Image(
                painter = painterResource(
                    id = R.drawable.pokeswap_logo,
                ),
                contentDescription = stringResource(id = R.string.home_coil_image_logo_desc),
                modifier = Modifier
                    .width(300.dp)
                    .height(200.dp),
            )

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(
                    space = 20.dp,
                    alignment = Alignment.CenterVertically,
                ),
                modifier = Modifier
                    .fillMaxWidth(),
            ) {
                FilledButton(
                    onClick = onCardLookupButtonClick,
                ) {
                    Text(
                        text = stringResource(id = R.string.home_card_lookup_button_label),
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .width(140.dp),
                    )
                }
                FilledButton(
                    onClick = onFavoriteButtonClick,
                ) {
                    Text(
                        text = stringResource(id = R.string.home_card_swap_button_label),
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .width(140.dp),
                    )
                }
                FilledButton(
                    onClick = onSettingsButtonClick,
                ) {
                    Text(
                        text = stringResource(id = R.string.home_settings_button_label),
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .width(140.dp),
                    )
                }
                FilledButton(
                    onClick = onCameraButtonClick,
                ) {
                    Text(
                        text = "Camera AI",
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .width(140.dp),
                    )
                }
            }

            Spacer(
                modifier = Modifier
                    .height(50.dp),
            )

            val favoriteCardLabel = viewState.favoritePokemon.id.takeIf { it.isNotEmpty() }
                ?.let {
                    "Current Favorite Card: ${viewState.favoritePokemon.name}"
                }
                ?: stringResource(id = R.string.home_favorite_pokemon_label_placeholder)

            Text(
                text = favoriteCardLabel,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(top = 45.dp),
            )
            Image(
                painter = rememberImagePainter(
                    data = viewState.favoritePokemon.images?.large
                        ?: R.drawable.pokemon_card_backside,
                    builder = {
                        placeholder(R.drawable.pokemon_card_backside)
                        error(R.drawable.pokemon_card_backside)
                    },
                ),
                contentDescription = stringResource(id = R.string.coil_image_placeholder),
                modifier = Modifier
                    .padding(30.dp)
                    .size(200.dp),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    PreviewScreen {
        HomeScreen(
            viewModelState = MutableStateFlow(
                HomeState(
                    isCurrencyLoading = false,
                    isPokemonLoading = false,
                    favoritePokemon = Pokemon(),
                ),
            ),
            onCardLookupButtonClick = {},
            onFavoriteButtonClick = {},
            onSettingsButtonClick = {},
            onCameraButtonClick = {},
        )
    }
}
