package com.ccspart2.pokeswap_android.network.domain.item

import com.ccspart2.pokeswap_android.data.model.AllPokemonResponse
import com.ccspart2.pokeswap_android.data.model.Pokemon

data class PokemonResponseItem(
    val count: Int = 0,
    val `data`: List<Pokemon> = emptyList(),
    val page: Int = 0,
    val pageSize: Int = 0,
    val totalCount: Int = 0,
)

fun AllPokemonResponse.toPokemonResponseItem() =
    PokemonResponseItem(count, data, page, pageSize, totalCount)
