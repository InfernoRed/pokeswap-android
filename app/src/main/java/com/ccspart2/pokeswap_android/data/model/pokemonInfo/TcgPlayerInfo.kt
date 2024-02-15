package com.ccspart2.pokeswap_android.data.model.pokemonInfo

data class TcgPlayerDetails(
    var url: String = "",
    var updatedAt: String = "",
    var prices: TcgPlayerPrices? = TcgPlayerPrices(),
)

data class TcgPlayerPrices(
    var holofoil: PriceDetails? = PriceDetails(),
    var reverseHolofoil: PriceDetails? = PriceDetails(),
)

data class PriceDetails(
    var low: Double? = 0.0,
    var mid: Double? = 0.0,
    var high: Double? = 0.0,
    var market: Double? = null,
    var directLow: Double? = null,
)
