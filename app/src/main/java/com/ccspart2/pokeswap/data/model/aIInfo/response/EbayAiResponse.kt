package com.ccspart2.pokeswap.data.model.aIInfo.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EbayAiResponse(
  @Json(name = "card_name") val cardName: String = "",
  @Json(name = "description") val description: String = "",
  @Json(name = "ebay_title") val ebayTitle: String = "",
) {
  companion object {
    fun mock(): EbayAiResponse {
      return EbayAiResponse(
        cardName = "Charizard",
        description =
          "This is a Charizard ex Pokémon card with 330 HP. It evolves from Charmeleon and features the ability 'Infernal Reign'. The attack 'Burning Darkness' deals 180+ damage and does 30 more damage for each Prize card your opponent has taken. The card is illustrated by 5ban Graphics and has the card number 054/097.",
        ebayTitle = "Charizard ex 330HP - Pokémon Card 054/097 - Infernal Reign & Burning Darkness",
      )
    }
  }
}
