package com.ccspart2.pokeswap.presentation.routes.cardDetails.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.ccspart2.pokeswap.R
import com.ccspart2.pokeswap.data.model.pokemonInfo.Pokemon
import com.ccspart2.pokeswap.presentation.core.ui.PreviewScreen
import com.ccspart2.pokeswap.presentation.core.ui.components.LoadingDialog
import com.ccspart2.pokeswap.presentation.routes.cardDetails.ui.components.CardDetailsSectionHeader
import com.ccspart2.pokeswap.presentation.routes.cardDetails.ui.components.CardDetailsSectionItem
import com.ccspart2.pokeswap.presentation.routes.cardDetails.viewmodel.CardDetailsState
import com.ccspart2.pokeswap.presentation.routes.cardDetails.viewmodel.CardDetailsViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun CardDetailsRoute(
    navController: NavController,
) {
    val viewModel: CardDetailsViewModel = hiltViewModel()
    CardDetailsScreen(
        viewModelState = viewModel.viewState,
    )
}

@Composable
private fun CardDetailsScreen(
    viewModelState: StateFlow<CardDetailsState>,
) {
    val viewState by viewModelState.collectAsState()

    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)
            .padding(20.dp),
    ) {
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
            Text(
                text = viewState.selectedPokemon.name,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .padding(vertical = 30.dp),
            )

            Image(
                painter = rememberImagePainter(
                    data = viewState.selectedPokemon.images?.large
                        ?: R.drawable.pokemon_card_backside,
                    builder = {
                        placeholder(R.drawable.pokemon_card_backside)
                        error(R.drawable.pokemon_card_backside)
                    },
                ),
                contentDescription = "Coil Image",
                modifier = Modifier
                    .size(300.dp),
            )
            Spacer(modifier = Modifier.weight(1f))

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth(),
            ) {
                item {
                    CardDetailsSectionItem(
                        sectionLabel = "Name",
                        valueLabel = viewState.selectedPokemon.name,
                    )
                    CardDetailsSectionItem(
                        sectionLabel = "Rarity",
                        valueLabel = viewState.selectedPokemon.rarity,
                    )
                    CardDetailsSectionItem(
                        sectionLabel = "Release Date",
                        valueLabel = viewState.selectedPokemon.set.releaseDate,
                    )
                    CardDetailsSectionHeader(headerLabel = "TCG Player")
                    CardDetailsSectionItem(
                        sectionLabel = "Holofoil",
                        valueLabel = viewState.holoPrice,
                    )
                    CardDetailsSectionItem(
                        sectionLabel = "Reverse Holofoil",
                        valueLabel = viewState.reverseHoloPrice,
                    )
                    CardDetailsSectionHeader(headerLabel = "Cardmarket")
                    CardDetailsSectionItem(
                        sectionLabel = "Trend",
                        valueLabel = viewState.trendPrice,
                    )
                    CardDetailsSectionItem(
                        sectionLabel = "Holofoil Avg 30 days",
                        valueLabel = viewState.avg30Price,
                    )
                    CardDetailsSectionItem(
                        sectionLabel = "Reverse Holofoil Avg 30 days",
                        valueLabel = viewState.reverseHoloAvg30Price,
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun CardDetailsScreenPreview() {
    PreviewScreen {
        CardDetailsScreen(
            viewModelState = MutableStateFlow(
                CardDetailsState(
                    Pokemon(
                        name = "Pikachu",
                    ),
                    isLoading = false,
                ),
            ),
        )
    }
}
