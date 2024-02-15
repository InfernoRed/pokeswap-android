package com.ccspart2.pokeswap_android.presentation.routes.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ccspart2.pokeswap_android.data.localData.dataStore.DataStoreManager
import com.ccspart2.pokeswap_android.network.domain.PokemonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val dataStoreManager: DataStoreManager,
    private val pokemonUseCase: PokemonUseCase,
) : ViewModel() {
    private val _viewState = MutableStateFlow(HomeState())
    val viewState = _viewState.asStateFlow()

    init {
        viewModelScope.launch {
            launch {
                pokemonUseCase().collect { pokemonList ->
                    if (pokemonList.isNotEmpty()) {
                        _viewState.update { state ->
                            state.copy(
                                isLoading = false,
                            )
                        }
                    }
                }
            }
            launch {
                dataStoreManager.getFromDataStore().collect { userPreferences ->
                    if (userPreferences.favPokemonId.isNotEmpty()) {
                        pokemonUseCase.findPokemonById(userPreferences.favPokemonId)
                            .collect { favoritePokemon ->
                                _viewState.update { state ->
                                    state.copy(
                                        favoritePokemonId = userPreferences.favPokemonId,
                                        favoritePokemon = favoritePokemon,
                                    )
                                }
                            }
                    }
                }
            }
        }
    }
}
