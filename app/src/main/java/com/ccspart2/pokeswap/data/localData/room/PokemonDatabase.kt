package com.ccspart2.pokeswap.data.localData.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ccspart2.pokeswap.data.model.currencyInfo.ExchangeRateResponse
import com.ccspart2.pokeswap.data.model.pokemonInfo.Pokemon

@Database(
    entities = [
        Pokemon::class,
        ExchangeRateResponse::class,
    ],
    version = 2,
    exportSchema = false,
)
abstract class PokemonDatabase : RoomDatabase() {
    abstract fun getPokemonDao(): PokemonDao
    abstract fun getExchangeDao(): ExchangeRatesDao
}
