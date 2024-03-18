package com.ccspart2.pokeswap.data.model.aIInfo.request

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AzureAiRequest(
    @Json(name = "max_tokens")
    val maxTokens: Int = 0,
    @Json(name = "messages")
    val messages: List<Message> = listOf(),
    @Json(name = "stream")
    val stream: Boolean = false,
    @Json(name = "temperature")
    val temperature: Double = 0.0,
    @Json(name = "top_p")
    val topP: Double = 0.0,
)
