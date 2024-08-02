package com.ccspart2.pokeswap.data.model.aIInfo.request

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Message(
  @Json(name = "content") val content: List<Content> = listOf(),
  @Json(name = "role") val role: String = "",
) {
  companion object {
    fun getSystemMessage(): Message {
      return Message(
        role = "system",
        content =
          listOf(
            Content(
              type = "text",
              text =
                "You are an AI assistant the helps sellers sell heir trading cards on Ebay. " +
                  "You provide a JSON response that include the card_name, description and a ebay_title to be showcased in Ebay. " +
                  "That Ebay title show be a good clickbait that would tempt the user to not " +
                  "only click on th listing but to buy it. The max for that should be 100 characters.",
            )
          ),
      )
    }

    fun getUserMessageWithImage(base64String: String?): Message {
      return Message(
        role = "user",
        content =
          listOf(
            Content(
              type = "image_url",
              imageUrl = ImageUrl(url = "data:image/jpeg;base64,$base64String"),
            ),
            Content(type = "text", text = ""),
          ),
      )
    }
  }
}
