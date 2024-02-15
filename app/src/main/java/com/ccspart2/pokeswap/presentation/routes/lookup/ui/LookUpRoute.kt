package com.ccspart2.pokeswap.presentation.routes.lookup.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ccspart2.pokeswap.R
import com.ccspart2.pokeswap.data.model.pokemonInfo.Pokemon
import com.ccspart2.pokeswap.presentation.navigation.NavigationItem
import com.ccspart2.pokeswap.presentation.routes.lookup.ui.components.PokemonLazyColumnItem
import com.ccspart2.pokeswap.presentation.routes.lookup.viewmodel.LookUpEvent
import com.ccspart2.pokeswap.presentation.routes.lookup.viewmodel.LookUpViewModel
import com.ccspart2.pokeswap.ui.theme.PokeSwapAndroidTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun LookUpRoute(navController: NavHostController) {
    val viewModel: LookUpViewModel = hiltViewModel()
    LookupScreen(
        searchTextState = viewModel.searchText,
        pokemonState = viewModel.pokemonSearch,
        onSearchTextValueChange = {
            viewModel.handleEvent(LookUpEvent.OnSearchTextChange(it))
        },
        onPokemonItemTap = { pokemonId ->
            navController.navigate("${NavigationItem.CardDetails.route}/$pokemonId")
        },
    )
}

@Composable
private fun LookupScreen(
    searchTextState: StateFlow<String>,
    pokemonState: StateFlow<List<Pokemon>>,
    onSearchTextValueChange: (String) -> Unit,
    onPokemonItemTap: (String) -> Unit,
) {
    val searchText by searchTextState.collectAsState()
    val searchedPokemon by pokemonState.collectAsState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)
            .padding(20.dp),
    ) {
        Image(
            painter = painterResource(
                id = R.drawable.pokeswap_logo,
            ),
            contentDescription = "PokeSwap Logo",
            modifier = Modifier
                .width(300.dp)
                .height(200.dp),
        )
        TextField(
            value = searchText,
            onValueChange = { onSearchTextValueChange(it) },
            modifier = Modifier
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(10.dp)),
            placeholder = {
                Text(
                    text = "Search for a Pokemon",
                )
            },
        )

        Spacer(modifier = Modifier.height(40.dp))

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.outline,
                    shape = RoundedCornerShape(10.dp),
                ),
            contentPadding = PaddingValues(10.dp),
        ) {
            items(searchedPokemon) { pokemon ->
                PokemonLazyColumnItem(
                    pokemon = pokemon,
                    onClick = {
                        onPokemonItemTap(it)
                    },
                )
            }
        }
    }
}

@Preview
@Composable
private fun LookupScreenPreview() {
    PokeSwapAndroidTheme {
        LookupScreen(
            searchTextState = MutableStateFlow(""),
            pokemonState = MutableStateFlow(listOf()),
            onSearchTextValueChange = {},
            onPokemonItemTap = {},
        )
    }
}
