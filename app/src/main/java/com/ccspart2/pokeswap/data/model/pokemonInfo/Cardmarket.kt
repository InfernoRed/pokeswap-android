package com.ccspart2.pokeswap.data.model.pokemonInfo

import androidx.room.Embedded
import androidx.room.Ignore
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Cardmarket(
    @Embedded
    @Json(name = "prices")
    var prices: Prices = Prices(),
    @Ignore
    @Json(name = "updatedAt")
    val updatedAt: String = "",
    @Ignore
    @Json(name = "url")
    val url: String = ""
)

@JsonClass(generateAdapter = true)
data class Prices(
    @Json(name = "averageSellPrice")
    val averageSellPrice: Double = 0.0,
    @Json(name = "avg1")
    val avg1: Double = 0.0,
    @Json(name = "avg30")
    val avg30: Double = 0.0,
    @Json(name = "avg7")
    val avg7: Double = 0.0,
    @Json(name = "lowPrice")
    val lowPrice: Double = 0.0,
    @Json(name = "lowPriceExPlus")
    val lowPriceExPlus: Double = 0.0,
    @Json(name = "reverseHoloAvg1")
    val reverseHoloAvg1: Double = 0.0,
    @Json(name = "reverseHoloAvg30")
    val reverseHoloAvg30: Double = 0.0,
    @Json(name = "reverseHoloAvg7")
    val reverseHoloAvg7: Double = 0.0,
    @Json(name = "reverseHoloLow")
    val reverseHoloLow: Double = 0.0,
    @Json(name = "reverseHoloSell")
    val reverseHoloSell: Double = 0.0,
    @Json(name = "reverseHoloTrend")
    val reverseHoloTrend: Double = 0.0,
    @Json(name = "suggestedPrice")
    val suggestedPrice: Double = 0.0,
    @Json(name = "trendPrice")
    val trendPrice: Double = 0.0,
)
