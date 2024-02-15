package com.ccspart2.pokeswap.data.model.pokemonInfo

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Ability(
    @Json(name = "name")
    val name: String = "",
    @Json(name = "text")
    val text: String = "",
    @Json(name = "type")
    val type: String = "",
)
