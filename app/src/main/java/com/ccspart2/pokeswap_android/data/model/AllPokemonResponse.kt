package com.ccspart2.pokeswap_android.data.model

data class AllPokemonResponse(
    val count: Int,
    val `data`: List<Pokemon>,
    val page: Int,
    val pageSize: Int,
    val totalCount: Int,
)