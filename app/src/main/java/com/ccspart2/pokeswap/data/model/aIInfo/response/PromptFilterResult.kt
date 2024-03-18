package com.ccspart2.pokeswap.data.model.aIInfo.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PromptFilterResult(
    @Json(name = "content_filter_results")
    val contentFilterResults: ContentFilterResults = ContentFilterResults(),
    @Json(name = "prompt_index")
    val promptIndex: Int = 0,
)
