package com.ccspart2.pokeswap_android.data.localData.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ccspart2.pokeswap_android.data.model.Pokemon

@Database(
    entities = [Pokemon::class],
    version = 1,
    exportSchema = false,
)
abstract class PokemonDatabase : RoomDatabase() {
    abstract fun getDao(): PokemonDao
}
