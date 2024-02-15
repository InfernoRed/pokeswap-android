package com.ccspart2.pokeswap.data.model.pokemonInfo

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Resistance(
    @Json(name = "type")
    val type: String = "",
    @Json(name = "value")
    val value: String = "",
)
