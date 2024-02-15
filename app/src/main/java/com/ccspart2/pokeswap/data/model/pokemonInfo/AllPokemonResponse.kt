package com.ccspart2.pokeswap.data.model.pokemonInfo

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AllPokemonResponse(
    @Json(name = "count")
    val count: Int = 0,
    @Json(name = "data")
    val `data`: List<Pokemon> = listOf(),
    @Json(name = "page")
    val page: Int = 0,
    @Json(name = "pageSize")
    val pageSize: Int = 0,
    @Json(name = "totalCount")
    val totalCount: Int = 0,
)
