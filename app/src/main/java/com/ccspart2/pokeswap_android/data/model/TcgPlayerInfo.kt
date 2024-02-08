package com.ccspart2.pokeswap_android.data.model

import com.squareup.moshi.Json

data class TcgPlayerInfo(
    var url: String = "",
    var updatedAt: String = "",
    var prices: TcgPlayerPrices = TcgPlayerPrices(),
)

data class TcgPlayerPrices(
    @Json(name = "holofoil") var holoFoil: PriceInfo? = null,
    @Json(name = "reverseHolofoil") var reverseHoloFoil: PriceInfo? = null,
)
