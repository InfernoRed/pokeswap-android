package com.ccspart2.pokeswap_android.presentation.routes.favorite.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ccspart2.pokeswap_android.data.localData.dataStore.DataStoreManager
import com.ccspart2.pokeswap_android.data.localData.dataStore.UserPreferences
import com.ccspart2.pokeswap_android.data.model.Pokemon
import com.ccspart2.pokeswap_android.network.domain.GetAllPokemonUseCase
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
    private val pokemonUseCase: GetAllPokemonUseCase,
    private val dataStoreManager: DataStoreManager,
) : ViewModel() {
    private val _viewState = MutableStateFlow(FavoriteState())
    val viewState = _viewState.asStateFlow()

    init {

        getAllPokemon()
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

    private fun getAllPokemon() {
        viewModelScope.launch {
            try {
                pokemonUseCase.getLocalPokemonData().collect { pokemonLocalList ->
                    if (pokemonLocalList.isEmpty()) {
                        val pokemonNetworkList = pokemonUseCase()
                        pokemonNetworkList?.data?.let {
                            _viewState.update { state ->
                                state.copy(
                                    pokemonList = it,
                                    leftDisplayedPokemon = it.random(),
                                    rightDisplayedPokemon = it.random(),
                                    pokemonCount = pokemonNetworkList.totalCount,
                                    isLoading = false,
                                )
                            }
                        }

                        pokemonNetworkList?.data?.forEach {
                            LogUtils.info(it.name)
                        }
                    } else {
                        _viewState.update { state ->
                            state.copy(
                                pokemonList = pokemonLocalList,
                                leftDisplayedPokemon = pokemonLocalList.random(),
                                rightDisplayedPokemon = pokemonLocalList.random(),
                                isLoading = false,
                            )
                        }
                    }
                }
            } catch (e: Exception) {
                LogUtils.error("App Connection Exception = ${e.message}")
            }
        }
    }
}
