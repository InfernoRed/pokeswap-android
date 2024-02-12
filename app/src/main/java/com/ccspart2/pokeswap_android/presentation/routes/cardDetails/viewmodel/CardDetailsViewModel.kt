package com.ccspart2.pokeswap_android.presentation.routes.cardDetails.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.ccspart2.pokeswap_android.utils.LogUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CardDetailsViewModel @Inject
constructor(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    init {
        val argument = savedStateHandle.get<String>("pokemonId").orEmpty()
        LogUtils.info("Argument desde el ViewModel = $argument")
    }
}
