package com.ccspart2.pokeswap.utils

object StringUtils {

    fun extractJsonString(input: String): String {
        return input.replace("```json", "")
            .replace("```", "")
            .trim()
    }
}