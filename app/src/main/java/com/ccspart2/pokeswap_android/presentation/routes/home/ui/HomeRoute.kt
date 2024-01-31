package com.ccspart2.pokeswap_android.presentation.routes.home.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.ccspart2.pokeswap_android.R
import com.ccspart2.pokeswap_android.presentation.core.ui.PreviewScreen
import com.ccspart2.pokeswap_android.presentation.core.ui.components.FilledButton
import com.ccspart2.pokeswap_android.presentation.core.ui.components.LoadingDialog
import com.ccspart2.pokeswap_android.presentation.routes.home.ui.components.PokedexTopBar
import com.ccspart2.pokeswap_android.presentation.routes.home.viewmodel.HomeEvent
import com.ccspart2.pokeswap_android.presentation.routes.home.viewmodel.HomeState
import com.ccspart2.pokeswap_android.presentation.routes.home.viewmodel.HomeViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun HomeRoute() {
    val viewModel: HomeViewModel = hiltViewModel()
    HomeScreen(
        viewModelState = viewModel.viewState,
        onLeftCardButtonClick = {
            viewModel.handleEvent(HomeEvent.RandomizeLeftCard)
        },
        onRightButtonClick = {
            viewModel.handleEvent(HomeEvent.RandomizeRightCard)
        },
    )
}

@Composable
private fun HomeScreen(
    viewModelState: StateFlow<HomeState>,
    onLeftCardButtonClick: () -> Unit,
    onRightButtonClick: () -> Unit,
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
                    text = "Choose your Favorite Pokemon",
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
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier
                        .fillMaxWidth(),
                ) {
                    Text(text = viewState.leftDisplayedPokemon.name)
                    Text(text = "VS")
                    Text(text = viewState.rightDisplayedPokemon.name)
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Image(
                        painter = rememberImagePainter(
                            data = viewState.leftDisplayedPokemon.images?.large,
                            builder = {
                                // Optional: Add image transformations
                                placeholder(R.drawable.pokemon_card_backside)
                                error(R.drawable.pokemon_card_backside)
                            },
                        ),
                        contentDescription = "Coil Image",
                        modifier = Modifier
                            .size(180.dp)
                            .clickable {
                                onLeftCardButtonClick()
                            },
                    )
                    Image(
                        painter = rememberImagePainter(
                            data = viewState.rightDisplayedPokemon.images?.large,
                            builder = {
                                // Optional: Add image transformations
                                placeholder(R.drawable.pokemon_card_backside)
                                error(R.drawable.pokemon_card_backside)
                            },
                        ),
                        contentDescription = "Coil Image",
                        modifier = Modifier
                            .size(180.dp)
                            .clickable {
                                onRightButtonClick()
                            },
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
                        text = "Left",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier
                            .padding(horizontal = 40.dp),
                    )
                }
                FilledButton(
                    onClick = { onRightButtonClick() },
                ) {
                    Text(
                        text = "Right",
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
private fun HomePreview() {
    PreviewScreen {
        HomeScreen(
            onRightButtonClick = {},
            onLeftCardButtonClick = {},
            viewModelState =
            MutableStateFlow(
                HomeState(
                    isLoading = false,
                ),
            ),
        )
    }
}
