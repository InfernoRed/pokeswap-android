package com.ccspart2.pokeswap.data.model.currencyInfo

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Entity(tableName = "currencyRate")
@JsonClass(generateAdapter = true)
data class ExchangeRateResponse(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @Json(name = "base_code")
    val baseCode: String = "",
    @Json(name = "conversion_rates")
    @Embedded
    val conversionRates: ConversionRates = ConversionRates(),
    @Json(name = "documentation")
    val documentation: String = "",
    @Json(name = "result")
    val result: String = "",
    @Json(name = "terms_of_use")
    val termsOfUse: String = "",
    @Json(name = "time_last_update_unix")
    val timeLastUpdateUnix: Int = 0,
    @Json(name = "time_last_update_utc")
    val timeLastUpdateUtc: String = "",
    @Json(name = "time_next_update_unix")
    val timeNextUpdateUnix: Int = 0,
    @Json(name = "time_next_update_utc")
    val timeNextUpdateUtc: String = "",
)
