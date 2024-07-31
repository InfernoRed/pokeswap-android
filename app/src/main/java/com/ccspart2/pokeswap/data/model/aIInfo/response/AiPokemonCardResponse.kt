package com.ccspart2.pokeswap.data.model.aIInfo.response

import com.squareup.moshi.Json

data class AiPokemonCardResponse(
    @Json(name = "name") val name: String = "",
    @Json(name = "team") val team: String = "",
    @Json(name = "card_brand") val cardBrand: String = "",
    @Json(name = "number") val number: String = "",
    @Json(name = "year") val year: String = "",
    @Json(name = "auction_title") val auctionTitle: String = "",
    @Json(name = "special_feature") val specialFeature: String = "",
    @Json(name = "auction_description") val auctionDescription: String = "",
) {
    companion object {
        fun mock(): AiPokemonCardResponse {
            return AiPokemonCardResponse(
                name = "Charizard",
                team = "Team Rocket",
                cardBrand = "Pokemon",
                number = "LC01-EN005",
                year = "1999",
                specialFeature = "Limited Edition Charizard LC01-EN005 Pokemon Card",
                auctionDescription = "Rare collectible Limited Edition Dark Magician card from the Yu-Gi-Oh! series. This holographic card features the iconic spellcaster and is a must-have for any serious collector or player. Card number LC01-EN005 from the year 2020.",

            )
        }
    }
}
