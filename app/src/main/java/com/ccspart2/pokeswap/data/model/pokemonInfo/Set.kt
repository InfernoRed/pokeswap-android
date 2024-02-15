package com.ccspart2.pokeswap.data.model.pokemonInfo

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Set(
    @Json(name = "id")
    val id: String = "",
    @Json(name = "images")
    val images: ImagesX = ImagesX(),
    @Json(name = "legalities")
    val legalities: Legalities = Legalities(),
    @Json(name = "name")
    val name: String = "",
    @Json(name = "printedTotal")
    val printedTotal: Int = 0,
    @Json(name = "ptcgoCode")
    val ptcgoCode: String? = "",
    @Json(name = "releaseDate")
    val releaseDate: String = "",
    @Json(name = "series")
    val series: String = "",
    @Json(name = "total")
    val total: Int = 0,
    @Json(name = "updatedAt")
    val updatedAt: String = ""
)

@JsonClass(generateAdapter = true)
data class Legalities(
    @Json(name = "expanded")
    val expanded: String? = null,
    @Json(name = "unlimited")
    val unlimited: String = ""
)

@JsonClass(generateAdapter = true)
data class ImagesX(
    @Json(name = "logo")
    val logo: String = "",
    @Json(name = "symbol")
    val symbol: String = ""
)
