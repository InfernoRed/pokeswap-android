package com.ccspart2.pokeswap.data.model.pokemonInfo

data class SetInfo(
    var id: String = "",
    var name: String = "",
    var series: String = "",
    var printedTotal: Int = 0,
    var total: Int = 0,
    var legalities: Legalities = Legalities(),
    var ptcgoCode: String = "",
    var releaseDate: String = "",
    var updatedAt: String = "",
    var images: SetImages = SetImages(),
)

data class Legalities(
    var unlimited: String = "",
)

data class SetImages(
    var symbol: String = "",
    var logo: String = "",
)
