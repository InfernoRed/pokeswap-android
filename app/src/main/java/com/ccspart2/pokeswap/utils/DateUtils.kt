package com.ccspart2.pokeswap.utils

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {
    private const val DATE_FORMAT = "EEE, dd MMM yyyy HH:mm:ss Z"

    fun isDateWithin24Hours(dateString: String): Boolean {
        val dateFormat = SimpleDateFormat(DATE_FORMAT, Locale.US)
        val date = dateFormat.parse(dateString)

        date?.let {
            val currentTime = Calendar.getInstance().time
            val differenceInMillis = currentTime.time - date.time
            val hoursDifference = differenceInMillis / (60 * 60 * 1000)

            return hoursDifference < 24
        }

        // If parsing fails or date is null, return false
        return false
    }
}
