package com.ccspart2.pokeswap.presentation.routes.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ccspart2.pokeswap.data.localData.dataStore.DataStoreManager
import com.ccspart2.pokeswap.network.domain.CurrencyExchangeUseCase
import com.ccspart2.pokeswap.network.domain.PokemonUseCase
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
    private val currencyExchangeUseCase: CurrencyExchangeUseCase,
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
                                isPokemonLoading = false,
                            )
                        }
                    }
                }
            }
            launch {
                currencyExchangeUseCase().collect {
                    it?.let { response ->
                        if (response.result == "success") {
                            _viewState.update { state ->
                                state.copy(
                                    isCurrencyLoading = false,
                                )
                            }
                        }
                    }
                }
            }
            launch {
                dataStoreManager.getFavPokemonFromDataStore().collect { favoritePokemonId ->
                    if (favoritePokemonId.isNotEmpty()) {
                        pokemonUseCase.findPokemonById(favoritePokemonId)
                            .collect { favoritePokemon ->
                                _viewState.update { state ->
                                    state.copy(
                                        favoritePokemonId = favoritePokemonId,
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
