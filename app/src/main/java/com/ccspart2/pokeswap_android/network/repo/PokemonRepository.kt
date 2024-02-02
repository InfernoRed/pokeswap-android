package com.ccspart2.pokeswap_android.network.repo

import com.ccspart2.pokeswap_android.data.localData.room.PokemonDatabase
import com.ccspart2.pokeswap_android.data.model.Pokemon
import com.ccspart2.pokeswap_android.network.data.PokemonService
import com.ccspart2.pokeswap_android.network.domain.item.PokemonResponseItem
import com.ccspart2.pokeswap_android.network.domain.item.toPokemonResponseItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PokemonRepository @Inject
constructor(
    private val pokemonService: PokemonService,
    private val db: PokemonDatabase,
) {

    fun getAllLocalPokemonData(): Flow<MutableList<Pokemon>> {
        return db.getDao().getAllPokemon()
    }

    suspend fun getAllPokemonData(): PokemonResponseItem? {
        val response = pokemonService.getAllPokemon()?.toPokemonResponseItem()

        response?.run {
            data.let { pokemonList ->
                db.getDao().insertAll(pokemonList.toMutableList())
            }
        }

        return response
    }
}
