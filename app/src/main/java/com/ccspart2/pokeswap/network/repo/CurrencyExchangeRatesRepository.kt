package com.ccspart2.pokeswap.network.repo

import com.ccspart2.pokeswap.data.model.currencyInfo.ConversionRates
import com.ccspart2.pokeswap.network.api.currency.CurrencyExchangeRatesServices
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CurrencyExchangeRatesRepository @Inject constructor(
    private val service: CurrencyExchangeRatesServices,
) {
    suspend fun getAllExchangeRates(): Flow<ConversionRates> {
        return flow {
            val response = service.getAllExchangeRates()
            response?.conversionRates?.let { conversionRates ->
                emit(conversionRates)
            }
        }
    }
}
