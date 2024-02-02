package com.ccspart2.pokeswap_android.presentation.routes.home.ui

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ccspart2.pokeswap_android.R
import com.ccspart2.pokeswap_android.presentation.core.ui.PreviewScreen
import com.ccspart2.pokeswap_android.presentation.core.ui.components.FilledButton
import com.ccspart2.pokeswap_android.presentation.core.ui.components.LoadingDialog
import com.ccspart2.pokeswap_android.presentation.navigation.NavigationItem
import com.ccspart2.pokeswap_android.presentation.routes.home.viewmodel.HomeState
import com.ccspart2.pokeswap_android.presentation.routes.home.viewmodel.HomeViewModel
import com.ccspart2.pokeswap_android.utils.LogUtils
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun HomeRoute(
    navController: NavController,
) {
    val viewModel: HomeViewModel = hiltViewModel()
    val context = LocalContext.current
    HomeScreen(
        viewModelState = viewModel.viewState,
        onCardLookupButtonClick = {
            Toast.makeText(context, "Card Lookup is under construction", Toast.LENGTH_LONG).show()
        },
        onMyDeckButtonClick = {
            Toast.makeText(context, "My Deck is under construction", Toast.LENGTH_LONG).show()
        },
        onFavoriteButtonClick = {
            navController.navigate(NavigationItem.Favorite.route)
        },
    )
}

@Composable
private fun HomeScreen(
    viewModelState: StateFlow<HomeState>,
    onCardLookupButtonClick: () -> Unit,
    onMyDeckButtonClick: () -> Unit,
    onFavoriteButtonClick: () -> Unit,
) {
    val viewState by viewModelState.collectAsState()

    // TODO Eliminate when Favorite Pokemon Display is implemented
    if (viewState.favoritePokemonId.isEmpty()) {
        LogUtils.info("No Favorite Pokemon Selected")
    } else {
        LogUtils.info("The selected Favorite Pokemon is : ${viewState.favoritePokemonId}")
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)
            .padding(bottom = 100.dp),
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
            Image(
                painter = painterResource(
                    id = R.drawable.pokeswap_logo,
                ),
                contentDescription = "PokeSwap Logo",
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
                        text = "Card Lookup",
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .width(140.dp),
                    )
                }
                FilledButton(
                    onClick = onMyDeckButtonClick,
                ) {
                    Text(
                        text = "My Deck",
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
                        text = "Favorite Card",
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .width(140.dp),
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    PreviewScreen {
        HomeScreen(
            viewModelState = MutableStateFlow(
                HomeState(),
            ),
            onCardLookupButtonClick = {},
            onFavoriteButtonClick = {},
            onMyDeckButtonClick = {},
        )
    }
}
