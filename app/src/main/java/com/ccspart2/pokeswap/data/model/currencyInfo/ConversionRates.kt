package com.ccspart2.pokeswap.data.model.currencyInfo

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ConversionRates(
    @Json(name = "AUD")
    val aUD: Double = 0.0,
    @Json(name = "CAD")
    val cAD: Double = 0.0,
    @Json(name = "CHF")
    val cHF: Double = 0.0,
    @Json(name = "EUR")
    val eUR: Int = 0,
    @Json(name = "GBP")
    val gBP: Double = 0.0,
    @Json(name = "HKD")
    val hKD: Double = 0.0,
    @Json(name = "JPY")
    val jPY: Double = 0.0,
    @Json(name = "NZD")
    val nZD: Double = 0.0,
    @Json(name = "USD")
    val uSD: Double = 0.0,
)
