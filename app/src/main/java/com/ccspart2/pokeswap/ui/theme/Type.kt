package com.ccspart2.pokeswap.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.ccspart2.pokeswap.R

val PokemonFont = FontFamily(
    Font(R.font.pokemon_solid),
)

val Typography = Typography(
    displayMedium = TextStyle(
        fontSize = 48.sp,
        lineHeight = 56.sp,
        fontFamily = PokemonFont,
        fontWeight = FontWeight(400),
    ),
    displaySmall = TextStyle(
        fontSize = 36.sp,
        lineHeight = 44.sp,
        fontFamily = PokemonFont,
        fontWeight = FontWeight(400),
    ),
    headlineLarge = TextStyle(
        fontSize = 34.sp,
        lineHeight = 42.sp,
        fontFamily = PokemonFont,
        fontWeight = FontWeight(400),
        letterSpacing = 0.25.sp,
    ),
    headlineSmall = TextStyle(
        fontSize = 24.sp,
        lineHeight = 32.sp,
        fontFamily = PokemonFont,
        fontWeight = FontWeight(400),
    ),
    titleLarge = TextStyle(
        fontSize = 22.sp,
        lineHeight = 28.sp,
        fontFamily = PokemonFont,
        fontWeight = FontWeight(400),
    ),
    titleMedium = TextStyle(
        fontSize = 16.sp,
        lineHeight = 24.sp,
        fontFamily = PokemonFont,
        fontWeight = FontWeight(500),
    ),
    titleSmall = TextStyle(
        fontSize = 14.sp,
        lineHeight = 20.sp,
        fontFamily = PokemonFont,
        fontWeight = FontWeight(500),
    ),
    bodyLarge = TextStyle(
        fontSize = 16.sp,
        lineHeight = 24.sp,
        fontFamily = PokemonFont,
        fontWeight = FontWeight(400),
    ),
    bodyMedium = TextStyle(
        fontSize = 14.sp,
        lineHeight = 20.sp,
        fontFamily = PokemonFont,
        fontWeight = FontWeight(400),
    ),
    bodySmall = TextStyle(
        fontSize = 12.sp,
        lineHeight = 16.sp,
        fontFamily = PokemonFont,
        fontWeight = FontWeight(400),
    ),
)
