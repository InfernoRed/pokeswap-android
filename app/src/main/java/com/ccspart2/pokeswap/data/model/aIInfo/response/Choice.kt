package com.ccspart2.pokeswap.data.model.aIInfo.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Choice(
    @Json(name = "content_filter_results")
    val contentFilterResults: ContentFilterResults = ContentFilterResults(),
    @Json(name = "finish_reason")
    val finishReason: Any? = null,
    @Json(name = "index")
    val index: Int = 0,
    @Json(name = "message")
    val message: Message = Message(),
)
