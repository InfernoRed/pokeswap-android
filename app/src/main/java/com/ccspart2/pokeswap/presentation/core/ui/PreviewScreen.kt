package com.ccspart2.pokeswap.presentation.core.ui

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.ccspart2.pokeswap.ui.theme.PokeSwapAndroidTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PreviewScreen(
    content: @Composable () -> Unit,
) {
    PokeSwapAndroidTheme {
        Scaffold {
            content()
        }
    }
}
