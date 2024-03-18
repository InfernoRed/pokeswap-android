package com.ccspart2.pokeswap.data.model.aIInfo.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Usage(
    @Json(name = "completion_tokens")
    val completionTokens: Int = 0,
    @Json(name = "prompt_tokens")
    val promptTokens: Int = 0,
    @Json(name = "total_tokens")
    val totalTokens: Int = 0,
)
