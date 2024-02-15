package com.ccspart2.pokeswap.data.model.pokemonInfo

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Attack(
    @Json(name = "convertedEnergyCost")
    val convertedEnergyCost: Int = 0,
    @Json(name = "cost")
    val cost: List<String> = listOf(),
    @Json(name = "damage")
    val damage: String = "",
    @Json(name = "name")
    val name: String = "",
    @Json(name = "text")
    val text: String = ""
)
