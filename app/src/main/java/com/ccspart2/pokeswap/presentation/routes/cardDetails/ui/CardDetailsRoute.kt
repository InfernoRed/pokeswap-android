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
import androidx.compose.ui.res.stringResource
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
                contentDescription = stringResource(id = R.string.coil_image_placeholder),
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
                        sectionLabel = stringResource(id = R.string.card_details_name_label),
                        valueLabel = viewState.selectedPokemon.name,
                    )
                    CardDetailsSectionItem(
                        sectionLabel = stringResource(id = R.string.card_details_rarity_label),
                        valueLabel = viewState.selectedPokemon.rarity,
                    )
                    CardDetailsSectionItem(
                        sectionLabel = stringResource(id = R.string.card_details_release_date_label),
                        valueLabel = viewState.selectedPokemon.set.releaseDate,
                    )
                    CardDetailsSectionHeader(
                        headerLabel = stringResource(id = R.string.card_details_TCG_section_label),
                    )
                    CardDetailsSectionItem(
                        sectionLabel = stringResource(id = R.string.card_details_holofoil_label),
                        valueLabel = viewState.holoPrice,
                    )
                    CardDetailsSectionItem(
                        sectionLabel = stringResource(id = R.string.card_details_reverse_holofoil_label),
                        valueLabel = viewState.reverseHoloPrice,
                    )
                    CardDetailsSectionHeader(
                        headerLabel = stringResource(id = R.string.card_details_card_market_section_label),
                    )
                    CardDetailsSectionItem(
                        sectionLabel = stringResource(id = R.string.card_details_trend_label),
                        valueLabel = viewState.trendPrice,
                    )
                    CardDetailsSectionItem(
                        sectionLabel = stringResource(id = R.string.card_details_holofoil_30_avg_label),
                        valueLabel = viewState.avg30Price,
                    )
                    CardDetailsSectionItem(
                        sectionLabel = stringResource(id = R.string.card_details_reverse_holofoil_30_avg_label),
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
