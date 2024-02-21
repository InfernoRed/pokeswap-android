package com.ccspart2.pokeswap.network.repo

import com.ccspart2.pokeswap.data.localData.room.PokemonDatabase
import com.ccspart2.pokeswap.data.model.currencyInfo.ConversionRates
import com.ccspart2.pokeswap.data.model.currencyInfo.ExchangeRateResponse
import com.ccspart2.pokeswap.network.api.currency.CurrencyExchangeRatesServices
import com.ccspart2.pokeswap.utils.DateUtils
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class CurrencyExchangeRatesRepository @Inject constructor(
    private val service: CurrencyExchangeRatesServices,
    private val db: PokemonDatabase,
) {
    @OptIn(ExperimentalCoroutinesApi::class)
    suspend fun getAllExchangeRates(): Flow<ExchangeRateResponse?> {
        return db.getExchangeDao().getRatesFlow().flatMapLatest { localRates ->
            if (localRates.isEmpty() || !DateUtils.isDateWithin24Hours(localRates.first().timeLastUpdateUtc)) {
                service.getAllExchangeRates()?.let { response ->
                    db.getExchangeDao().insert(
                        response.copy(
                            id = 1,
                        ),
                    )
                    flowOf(response)
                } ?: flowOf(null)
            } else {
                flowOf(localRates.first())
            }
        }
    }

    fun getAllLocalExchangeRates(): ConversionRates {
        return db.getExchangeDao().getRates().first().conversionRates
    }
}
