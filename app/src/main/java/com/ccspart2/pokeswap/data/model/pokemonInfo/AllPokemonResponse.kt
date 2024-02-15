package com.ccspart2.pokeswap.data.model.pokemonInfo

data class AllPokemonResponse(
    val count: Int,
    val `data`: List<Pokemon>,
    val page: Int,
    val pageSize: Int,
    val totalCount: Int,
)
