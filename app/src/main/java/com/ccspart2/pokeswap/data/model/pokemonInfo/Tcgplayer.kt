package com.ccspart2.pokeswap.data.model.pokemonInfo

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Tcgplayer(
    @Json(name = "prices")
    val prices: PricesX? = PricesX(),
    @Json(name = "updatedAt")
    val updatedAt: String = "",
    @Json(name = "url")
    val url: String = ""
)

@JsonClass(generateAdapter = true)
data class PricesX(
    @Json(name = "holofoil")
    val holofoil: Holofoil? = null,
    @Json(name = "normal")
    val normal: Normal? = null,
    @Json(name = "reverseHolofoil")
    val reverseHolofoil: ReverseHolofoil? = null,
    @Json(name = "1stEditionHolofoil")
    val stEditionHolofoil: StEditionHolofoil? = null,
    @Json(name = "unlimitedHolofoil")
    val unlimitedHolofoil: UnlimitedHolofoil? = null
)

@JsonClass(generateAdapter = true)
data class Holofoil(
    @Json(name = "directLow")
    val directLow: Double? = null,
    @Json(name = "high")
    val high: Double = 0.0,
    @Json(name = "low")
    val low: Double = 0.0,
    @Json(name = "market")
    val market: Double = 0.0,
    @Json(name = "mid")
    val mid: Double = 0.0
)

@JsonClass(generateAdapter = true)
data class StEditionHolofoil(
    @Json(name = "directLow")
    val directLow: Any? = null,
    @Json(name = "high")
    val high: Double = 0.0,
    @Json(name = "low")
    val low: Double = 0.0,
    @Json(name = "market")
    val market: Double = 0.0,
    @Json(name = "mid")
    val mid: Double = 0.0
)

@JsonClass(generateAdapter = true)
data class Normal(
    @Json(name = "directLow")
    val directLow: Double? = null,
    @Json(name = "high")
    val high: Double = 0.0,
    @Json(name = "low")
    val low: Double = 0.0,
    @Json(name = "market")
    val market: Double? = null,
    @Json(name = "mid")
    val mid: Double = 0.0
)

@JsonClass(generateAdapter = true)
data class ReverseHolofoil(
    @Json(name = "directLow")
    val directLow: Double? = null,
    @Json(name = "high")
    val high: Double = 0.0,
    @Json(name = "low")
    val low: Double = 0.0,
    @Json(name = "market")
    val market: Double? = null,
    @Json(name = "mid")
    val mid: Double = 0.0
)

@JsonClass(generateAdapter = true)
data class UnlimitedHolofoil(
    @Json(name = "directLow")
    val directLow: Double? = null,
    @Json(name = "high")
    val high: Double = 0.0,
    @Json(name = "low")
    val low: Double = 0.0,
    @Json(name = "market")
    val market: Double = 0.0,
    @Json(name = "mid")
    val mid: Double = 0.0
)
