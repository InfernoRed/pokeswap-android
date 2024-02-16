package com.ccspart2.pokeswap.presentation.routes.cardDetails.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ccspart2.pokeswap.network.domain.PokemonUseCase
import com.ccspart2.pokeswap.presentation.navigation.NavigationArguments
import com.ccspart2.pokeswap.utils.CurrencyUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CardDetailsViewModel @Inject
constructor(
    savedStateHandle: SavedStateHandle,
    private val pokemonUseCase: PokemonUseCase,
    private val currencyUtils: CurrencyUtils,
) : ViewModel() {

    private val _viewState = MutableStateFlow(CardDetailsState())
    val viewState = _viewState.asStateFlow()

    init {
        val argument = savedStateHandle.get<String>(NavigationArguments.POKEMON_ID.key).orEmpty()
        if (argument.isNotEmpty()) {
            viewModelScope.launch {
                pokemonUseCase.findPokemonById(argument).collect { selectedPokemon ->
                    _viewState.update { state ->
                        state.copy(
                            selectedPokemon = selectedPokemon,
                            isLoading = false,
                            holoPrice = currencyUtils.convertCurrencyFromEURtoUSD(selectedPokemon.tcgplayer.prices?.holofoil?.market ?: 0.00),
                            reverseHoloPrice = currencyUtils.convertCurrencyFromEURtoUSD(selectedPokemon.tcgplayer.prices?.reverseHolofoil?.market ?: 0.00),
                            trendPrice = currencyUtils.convertCurrencyFromEURtoUSD(selectedPokemon.cardmarket.prices.trendPrice),
                            avg30Price = currencyUtils.convertCurrencyFromEURtoUSD(selectedPokemon.cardmarket.prices.avg30),
                            reverseHoloAvg30Price = currencyUtils.convertCurrencyFromEURtoUSD(selectedPokemon.cardmarket.prices.reverseHoloAvg30),

                        )
                    }
                }
            }
        }
    }
}
