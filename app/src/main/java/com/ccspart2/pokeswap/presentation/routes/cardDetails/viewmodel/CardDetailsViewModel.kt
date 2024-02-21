package com.ccspart2.pokeswap.presentation.routes.cardDetails.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ccspart2.pokeswap.data.localData.dataStore.DataStoreManager
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
    private val dataStoreManager: DataStoreManager,
) : ViewModel() {

    private val _viewState = MutableStateFlow(CardDetailsState())
    val viewState = _viewState.asStateFlow()

    init {
        val argument = savedStateHandle.get<String>(NavigationArguments.POKEMON_ID.key).orEmpty()
        if (argument.isNotEmpty()) {
            viewModelScope.launch {
                pokemonUseCase.findPokemonById(argument).collect { selectedPokemon ->
                    dataStoreManager.getSelectedCurrencyFromDataStore().collect { isEuro ->
                        _viewState.update { state ->
                            state.copy(
                                selectedPokemon = selectedPokemon,
                                isLoading = false,
                                holoPrice = if (isEuro) {
                                    currencyUtils.doubleToEuroString(
                                        selectedPokemon.tcgplayer.prices?.holofoil?.market ?: 0.00,
                                    )
                                } else {
                                    currencyUtils.convertCurrencyFromEURtoUSD(
                                        selectedPokemon.tcgplayer.prices?.holofoil?.market ?: 0.00,
                                    )
                                },
                                reverseHoloPrice = if (isEuro) {
                                    currencyUtils.doubleToEuroString(
                                        selectedPokemon.tcgplayer.prices?.reverseHolofoil?.market
                                            ?: 0.00,
                                    )
                                } else {
                                    currencyUtils.convertCurrencyFromEURtoUSD(
                                        selectedPokemon.tcgplayer.prices?.reverseHolofoil?.market
                                            ?: 0.00,
                                    )
                                },
                                trendPrice = if (isEuro) {
                                    currencyUtils.doubleToEuroString(
                                        selectedPokemon.cardmarket.prices.trendPrice,
                                    )
                                } else {
                                    currencyUtils.convertCurrencyFromEURtoUSD(
                                        selectedPokemon.cardmarket.prices.trendPrice,
                                    )
                                },
                                avg30Price = if (isEuro) {
                                    currencyUtils.doubleToEuroString(
                                        selectedPokemon.cardmarket.prices.avg30,
                                    )
                                } else {
                                    currencyUtils.convertCurrencyFromEURtoUSD(
                                        selectedPokemon.cardmarket.prices.avg30,
                                    )
                                },
                                reverseHoloAvg30Price = if (isEuro) {
                                    currencyUtils.doubleToEuroString(
                                        selectedPokemon.cardmarket.prices.reverseHoloAvg30,
                                    )
                                } else {
                                    currencyUtils.convertCurrencyFromEURtoUSD(
                                        selectedPokemon.cardmarket.prices.reverseHoloAvg30,
                                    )
                                },
                            )
                        }
                    }
                }
            }
        }
    }
}
