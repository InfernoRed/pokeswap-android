package com.ccspart2.pokeswap.data.model.aIInfo.request

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ImageUrl(
    @Json(name = "url")
    val url: String = "",
)
