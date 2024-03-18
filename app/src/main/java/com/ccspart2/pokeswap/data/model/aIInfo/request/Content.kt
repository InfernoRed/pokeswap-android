package com.ccspart2.pokeswap.data.model.aIInfo.request

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Content(
    @Json(name = "image_url")
    val imageUrl: ImageUrl? = null,
    @Json(name = "text")
    val text: String? = null,
    @Json(name = "type")
    val type: String = "",
)
