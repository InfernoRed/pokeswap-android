package com.ccspart2.pokeswap.data.localData.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ccspart2.pokeswap.data.model.currencyInfo.ExchangeRateResponse
import kotlinx.coroutines.flow.Flow

@Dao
interface ExchangeRatesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(rates: MutableList<ExchangeRateResponse>)

    @Query("SELECT * FROM currencyRate")
    fun getAllRatesFlow(): Flow<MutableList<ExchangeRateResponse>>

    @Query("SELECT * FROM currencyRate")
    fun getAllRates(): MutableList<ExchangeRateResponse>
}
