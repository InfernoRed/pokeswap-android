package com.ccspart2.pokeswap.data.model.aIInfo.request

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Message(
    @Json(name = "content")
    val content: List<Content> = listOf(),
    @Json(name = "role")
    val role: String = "",
)
