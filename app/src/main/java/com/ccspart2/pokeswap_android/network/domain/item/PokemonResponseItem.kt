package com.ccspart2.pokeswap_android.network.domain.item

import com.ccspart2.pokeswap_android.data.model.Pokemon
import com.ccspart2.pokeswap_android.data.model.PokemonResponse

data class PokemonResponseItem(
    val count: Int = 0,
    val `data`: List<Pokemon> = emptyList(),
    val page: Int = 0,
    val pageSize: Int = 0,
    val totalCount: Int = 0,
)

fun PokemonResponse.toPokemonResponseItem() =
    PokemonResponseItem(count, data, page, pageSize, totalCount)
