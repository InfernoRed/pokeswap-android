package com.ccspart2.pokeswap.data.model.aIInfo.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AzureAiResponse(
    @Json(name = "choices")
    val choices: List<Choice> = listOf(),
    @Json(name = "created")
    val created: Int = 0,
    @Json(name = "id")
    val id: String = "",
    @Json(name = "model")
    val model: String = "",
    @Json(name = "object")
    val completionObject: String = "",
    @Json(name = "prompt_filter_results")
    val promptFilterResults: List<PromptFilterResult> = listOf(),
    @Json(name = "usage")
    val usage: Usage = Usage(),
)
