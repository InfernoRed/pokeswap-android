package com.ccspart2.pokeswap.data.model.aIInfo.request

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Message(
    @Json(name = "content")
    val content: List<Content> = listOf(),
    @Json(name = "role")
    val role: String = "",
) {
    companion object {
        fun getSystemMessage(): Message {
            return Message(
                role = "system",
                content = listOf(
                    Content(
                        type = "text",
                        text = "You are an AI assistant that helps people find information about trading cards.",
                    ),
                ),
            )
        }

        fun getUserMessageWithImage(base64String: String?): Message {
            return Message(
                role = "user",
                content = listOf(
                    Content(
                        type = "image_url",
                        imageUrl = ImageUrl(
                            url = "data:image/jpeg;base64,$base64String",
                        ),
                    ),
                    Content(
                        type = "text",
                        text = "Tell me all the information you can gather from this card.",
                    ),
                ),
            )
        }
    }
}
