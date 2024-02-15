package com.ccspart2.pokeswap.data.model.pokemonInfo

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokemonResponse(
    val `data`: Pokemon,
)
