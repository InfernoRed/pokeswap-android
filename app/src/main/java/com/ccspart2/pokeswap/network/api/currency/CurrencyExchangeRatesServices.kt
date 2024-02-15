package com.ccspart2.pokeswap.network.api.currency

import com.ccspart2.pokeswap.data.model.currencyInfo.ExchangeRateResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CurrencyExchangeRatesServices @Inject constructor(
    private val currencyExchangeRatesApi: CurrencyExchangeRatesApi,
) {
    suspend fun getAllExchangeRates(): ExchangeRateResponse? {
        return withContext(Dispatchers.IO) {
            val response = currencyExchangeRatesApi.getAllExchangeRates()
            response.body()
        }
    }
}
