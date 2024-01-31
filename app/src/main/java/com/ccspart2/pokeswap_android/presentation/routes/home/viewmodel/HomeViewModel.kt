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
                                    currentlyDisplayedImageUrl = it.first().images?.large,
                                    pokemonCount = pokemonNetworkList.totalCount,
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
                                currentlyDisplayedImageUrl = pokemonLocalList.first().images?.large,
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
