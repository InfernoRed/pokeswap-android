package com.ccspart2.pokeswap.network.repo

import com.ccspart2.pokeswap.data.localData.room.PokemonDatabase
import com.ccspart2.pokeswap.data.model.pokemonInfo.Pokemon
import com.ccspart2.pokeswap.network.api.pokemon.PokemonService
import com.ccspart2.pokeswap.utils.LogUtils
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class PokemonRepository @Inject
constructor(
    private val pokemonService: PokemonService,
    private val db: PokemonDatabase,
) {
    @OptIn(ExperimentalCoroutinesApi::class)
    suspend fun getAllPokemonData(): Flow<MutableList<Pokemon>> {
        return db.getPokemonDao().getAllPokemon()
            .flatMapLatest { localPokemonList ->
                if (localPokemonList.isEmpty()) {
                    val response = pokemonService.getAllPokemon()

                    response?.data?.let { pokemonList ->
                        db.getPokemonDao().insertAll(pokemonList.toMutableList())
                        return@let flowOf(pokemonList.toMutableList())
                    }
                }
                return@flatMapLatest flowOf(localPokemonList)
            }
    }

    // TODO Make Local ROOM query before looking it Online
    suspend fun getPokemonById(pokemonId: String): Flow<Pokemon> = flow {
        try {
            pokemonService.getPokemonById(pokemonId)?.let {
                emit(it.data)
            }
        } catch (e: Exception) {
            LogUtils.error("Network Error: ${e.message}")
        }
    }
}
