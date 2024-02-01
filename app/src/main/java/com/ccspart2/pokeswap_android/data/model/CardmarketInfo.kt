package com.ccspart2.pokeswap_android.data.model

import androidx.room.Embedded
import androidx.room.Ignore

data class CardmarketInfo(
    @Ignore val url: String = "",
    @Ignore val updatedAt: String = "",
    @Embedded val prices: CardmarketPrices = CardmarketPrices(),
)

data class CardmarketPrices(
    val averageSellPrice: Double = 0.0,
    val lowPrice: Double = 0.0,
    val trendPrice: Double = 0.0,
    val germanProLow: Double = 0.0,
    val suggestedPrice: Double = 0.0,
    val reverseHoloSell: Double = 0.0,
    val reverseHoloLow: Double = 0.0,
    val reverseHoloTrend: Double = 0.0,
    val lowPriceExPlus: Double = 0.0,
    val avg1: Double = 0.0,
    val avg7: Double = 0.0,
    val avg30: Double = 0.0,
    val reverseHoloAvg1: Double = 0.0,
    val reverseHoloAvg7: Double = 0.0,
    val reverseHoloAvg30: Double = 0.0,
)
