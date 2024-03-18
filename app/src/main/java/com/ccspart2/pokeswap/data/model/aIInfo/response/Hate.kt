package com.ccspart2.pokeswap.data.model.aIInfo.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Hate(
    @Json(name = "filtered")
    val filtered: Boolean = false,
    @Json(name = "severity")
    val severity: String = "",
)
