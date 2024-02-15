package com.ccspart2.pokeswap.network.api.currency

import com.ccspart2.pokeswap.data.model.currencyInfo.ExchangeRateResponse
import com.ccspart2.pokeswap.network.common.Constants
import retrofit2.Response
import retrofit2.http.GET

interface CurrencyExchangeRatesApi {

    @GET(Constants.EXCHANGE_RATE_ENDPOINT)
    suspend fun getAllExchangeRates(): Response<ExchangeRateResponse>
}
