package com.ccspart2.pokeswap.presentation.routes.cameraAi.viewModel

import android.graphics.Bitmap

data class CameraAIState(
    val name: String = "Hello World!",
    val capturedBitmap: Bitmap? = null,
    val base64BitMap: String = "",
)
