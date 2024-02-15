package com.ccspart2.pokeswap.presentation.routes.lookup.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ccspart2.pokeswap.data.model.pokemonInfo.Pokemon
import com.ccspart2.pokeswap.network.domain.PokemonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LookUpViewModel @Inject constructor(
    private val pokemonUseCase: PokemonUseCase,
) : ViewModel() {

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _pokemonSearch = MutableStateFlow(listOf<Pokemon>())
    val pokemonSearch = searchText
        .combine(_pokemonSearch) { text, pokemon ->
            if (text.isBlank()) {
                pokemon
            } else {
                pokemon.filter {
                    it.doesMatchSearchQuery(text)
                }
            }
        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(500),
            _pokemonSearch.value,
        )

    init {
        viewModelScope.launch {
            pokemonUseCase().collect { pokemonList ->
                if (pokemonList.isNotEmpty()) {
                    _pokemonSearch.value = pokemonList
                }
            }
        }
    }

    fun handleEvent(event: LookUpEvent) {
        when (event) {
            is LookUpEvent.OnSearchTextChange -> onSearchTextChange(event.text)
        }
    }

    private fun onSearchTextChange(text: String) {
        _searchText.value = text
    }
}
