package com.ccspart2.pokeswap_android.data.localData.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ccspart2.pokeswap_android.data.model.Pokemon
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(pokemon: MutableList<Pokemon>)

    @Query("SELECT * FROM pokemon ORDER BY name")
    fun getAllPokemon(): Flow<MutableList<Pokemon>>
}
