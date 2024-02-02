package com.ccspart2.pokeswap_android.network.repo

import com.ccspart2.pokeswap_android.data.localData.room.PokemonDatabase
import com.ccspart2.pokeswap_android.data.model.Pokemon
import com.ccspart2.pokeswap_android.network.data.PokemonService
import com.ccspart2.pokeswap_android.network.domain.item.toPokemonResponseItem
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class PokemonRepository @Inject
constructor(
    private val pokemonService: PokemonService,
    private val db: PokemonDatabase,
) {
    @OptIn(ExperimentalCoroutinesApi::class)
    suspend fun getAllPokemonData(): Flow<MutableList<Pokemon>> {
        return db.getDao().getAllPokemon()
            .flatMapLatest { localPokemonList ->
                if (localPokemonList.isEmpty()) {
                    val response = pokemonService.getAllPokemon()?.toPokemonResponseItem()

                    response?.data?.let { pokemonList ->
                        db.getDao().insertAll(pokemonList.toMutableList())
                        return@let flowOf(pokemonList.toMutableList())
                    }
                }
                return@flatMapLatest flowOf(localPokemonList)
            }
    }
}
