package com.ccspart2.pokeswap.network.domain

import com.ccspart2.pokeswap.data.model.currencyInfo.ExchangeRateResponse
import com.ccspart2.pokeswap.network.repo.CurrencyExchangeRatesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CurrencyExchangeUseCase @Inject constructor(
    private val currencyExchangeRatesRepository: CurrencyExchangeRatesRepository,
) {
    suspend operator fun invoke(): Flow<ExchangeRateResponse?> {
        return currencyExchangeRatesRepository.getAllExchangeRates()
    }
}
