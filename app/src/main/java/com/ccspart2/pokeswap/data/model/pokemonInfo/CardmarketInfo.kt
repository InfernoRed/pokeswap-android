package com.ccspart2.pokeswap.data.model.pokemonInfo

import androidx.room.Embedded
import androidx.room.Ignore

data class CardmarketInfo(
    @Ignore var url: String = "",
    @Ignore var updatedAt: String = "",
    @Embedded var prices: CardmarketPrices = CardmarketPrices(),
)

data class CardmarketPrices(
    var averageSellPrice: Double = 0.0,
    var lowPrice: Double = 0.0,
    var trendPrice: Double = 0.0,
    var germanProLow: Double = 0.0,
    var suggestedPrice: Double = 0.0,
    var reverseHoloSell: Double = 0.0,
    var reverseHoloLow: Double = 0.0,
    var reverseHoloTrend: Double = 0.0,
    var lowPriceExPlus: Double = 0.0,
    var avg1: Double = 0.0,
    var avg7: Double = 0.0,
    var avg30: Double = 0.0,
    var reverseHoloAvg1: Double = 0.0,
    var reverseHoloAvg7: Double = 0.0,
    var reverseHoloAvg30: Double = 0.0,
)
