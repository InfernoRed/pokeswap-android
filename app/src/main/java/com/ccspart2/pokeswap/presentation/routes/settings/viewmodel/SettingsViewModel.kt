package com.ccspart2.pokeswap.presentation.routes.settings.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ccspart2.pokeswap.data.localData.dataStore.DataStoreManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val dataStoreManager: DataStoreManager,
) : ViewModel() {
    private val _viewState = MutableStateFlow(SettingsState())
    val viewState = _viewState.asStateFlow()

    init {
        viewModelScope.launch {
            dataStoreManager.getSelectedCurrencyFromDataStore().collect {
                _viewState.update { state ->
                    state.copy(
                        isEuroSelected = it,
                    )
                }
            }
        }
    }

    fun handleEvents(event: SettingsEvent) {
        when (event) {
            is SettingsEvent.ToggleCurrency -> onToggleCurrency(event.selectEuro)
        }
    }

    private fun onToggleCurrency(selectEuro: Boolean) {
        viewModelScope.launch {
            dataStoreManager.saveCurrencySelectionToDataStore(selectEuro)
        }
        _viewState.update { state ->
            state.copy(
                isEuroSelected = selectEuro,
            )
        }
    }
}
