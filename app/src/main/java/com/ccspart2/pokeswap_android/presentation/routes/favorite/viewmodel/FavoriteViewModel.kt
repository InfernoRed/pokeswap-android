package com.ccspart2.pokeswap_android.presentation.routes.favorite.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ccspart2.pokeswap_android.data.localData.dataStore.DataStoreManager
import com.ccspart2.pokeswap_android.data.localData.dataStore.UserPreferences
import com.ccspart2.pokeswap_android.data.model.pokemonInfo.Pokemon
import com.ccspart2.pokeswap_android.network.domain.PokemonUseCase
import com.ccspart2.pokeswap_android.utils.LogUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject
constructor(
    private val pokemonUseCase: PokemonUseCase,
    private val dataStoreManager: DataStoreManager,
) : ViewModel() {
    private val _viewState = MutableStateFlow(FavoriteState())
    val viewState = _viewState.asStateFlow()

    init {
        viewModelScope.launch {
            pokemonUseCase().collect { pokemonList ->
                if (pokemonList.isNotEmpty()) {
                    _viewState.update { state ->
                        state.copy(
                            pokemonList = pokemonList,
                            leftDisplayedPokemon = pokemonList.random(),
                            rightDisplayedPokemon = pokemonList.random(),
                            isLoading = false,
                        )
                    }
                }
            }
        }
    }

    fun handleEvent(event: FavoriteEvent) {
        when (event) {
            FavoriteEvent.RandomizeLeftCard -> updateLeftCard()
            FavoriteEvent.RandomizeRightCard -> updateRightCard()
        }
    }

    private fun updateRightCard() {
        val currentPokemonList = viewState.value.pokemonList.toMutableList()

        if (currentPokemonList.size > 2) {
            currentPokemonList.remove(viewState.value.rightDisplayedPokemon)
            LogUtils.info("The current Pokemon list is: ${currentPokemonList.size} ")

            _viewState.update { state ->
                state.copy(
                    pokemonList = currentPokemonList,
                    rightDisplayedPokemon = randomPokemonCard(
                        state.leftDisplayedPokemon,
                        currentPokemonList,
                    ),
                )
            }
        } else {
            saveFavoritePokemonCard(viewState.value.leftDisplayedPokemon)
        }
    }

    private fun updateLeftCard() {
        val currentPokemonList = viewState.value.pokemonList.toMutableList()

        if (currentPokemonList.size > 2) {
            currentPokemonList.remove(viewState.value.leftDisplayedPokemon)
            LogUtils.info("The current Pokemon list is: ${currentPokemonList.size} ")

            _viewState.update { state ->
                state.copy(
                    pokemonList = currentPokemonList,
                    leftDisplayedPokemon = randomPokemonCard(
                        state.rightDisplayedPokemon,
                        currentPokemonList,
                    ),
                )
            }
        } else {
            saveFavoritePokemonCard(viewState.value.rightDisplayedPokemon)
        }
    }

    private fun saveFavoritePokemonCard(favoritePokemon: Pokemon) {
        viewModelScope.launch {
            dataStoreManager.saveToDataStore(
                UserPreferences(
                    favPokemonId = favoritePokemon.id,
                ),
            )
        }
        _viewState.update { state ->
            state.copy(
                favPokemon = favoritePokemon,
                favPokemonSelected = true,
            )
        }
    }

    private fun randomPokemonCard(
        otherPokemonDisplayed: Pokemon,
        currentPokemonList: List<Pokemon>,
    ): Pokemon {
        val filteredList = currentPokemonList.filter { it != otherPokemonDisplayed }
        return filteredList.random()
    }
}
