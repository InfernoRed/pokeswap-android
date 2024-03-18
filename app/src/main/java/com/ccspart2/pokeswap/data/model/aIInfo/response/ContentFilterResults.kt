package com.ccspart2.pokeswap.data.model.aIInfo.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ContentFilterResults(
    @Json(name = "hate")
    val hate: Hate = Hate(),
    @Json(name = "self_harm")
    val selfHarm: SelfHarm = SelfHarm(),
    @Json(name = "sexual")
    val sexual: Sexual = Sexual(),
    @Json(name = "violence")
    val violence: Violence = Violence(),
)
