package com.ccspart2.pokeswap.utils

import com.ccspart2.pokeswap.network.repo.CurrencyExchangeRatesRepository
import java.text.NumberFormat
import java.util.Currency
import javax.inject.Inject

class CurrencyUtils @Inject constructor(
    private val exchangeRatesRepository: CurrencyExchangeRatesRepository,
) {
    fun convertCurrencyFromEURtoUSD(amount: Double): String {
        val usdCurrencyRate = exchangeRatesRepository.getAllLocalExchangeRates().uSD

        if (usdCurrencyRate != 0.00) {
            val usdAmount = usdCurrencyRate * amount
            val currencyFormat = NumberFormat.getCurrencyInstance().apply {
                currency = Currency.getInstance("USD")
                maximumFractionDigits = 2
            }
            return currencyFormat.format(usdAmount)
        }
        return "$0.00"
    }
}
