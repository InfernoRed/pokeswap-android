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
                val pokemon = pokemonUseCase()
                _viewState.update { state ->
                    state.copy(
                        pokemonList = pokemon,
                        currentlyDisplayedImageUrl = pokemon?.data?.first()?.images?.large,
                        pokemonCount = pokemon?.count ?: 0,
                    )
                }
                pokemon?.data?.forEach {
                    LogUtils.info(it.name)
                }
            } catch (e: Exception) {
                LogUtils.error("No se pudo Conectar = ${e.message}")
            }
        }
    }
}
