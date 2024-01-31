package com.ccspart2.pokeswap_android.presentation.routes.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ccspart2.pokeswap_android.network.domain.GetAllPokemonUseCase
import com.ccspart2.pokeswap_android.utils.LogUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject
constructor(
    private val pokemonUseCase: GetAllPokemonUseCase,
) : ViewModel() {
    private val _viewState = MutableStateFlow(HomeState())
    val viewState = _viewState.asStateFlow()

    init {

        getAllPokemon()
    }

    fun handleEvent(event: HomeEvent) {
        when (event) {
            HomeEvent.RandomizeLeftCard -> updateLeftCard()
            HomeEvent.RandomizeRightCard -> updateRightCard()
        }
    }

    private fun updateRightCard() {
        _viewState.update { state ->
            state.copy(
                rightDisplayedPokemon = state.pokemonList.random(),
            )
        }
    }

    private fun updateLeftCard() {
        _viewState.update { state ->
            state.copy(
                leftDisplayedPokemon = state.pokemonList.random(),
            )
        }
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
