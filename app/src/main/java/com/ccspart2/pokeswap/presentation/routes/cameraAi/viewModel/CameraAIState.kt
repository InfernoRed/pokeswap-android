package com.ccspart2.pokeswap.presentation.routes.cameraAi.viewModel

import android.graphics.Bitmap
import com.ccspart2.pokeswap.data.model.aIInfo.response.AzureAiResponse

data class CameraAIState(
    val name: String = "Hello World!",
    val capturedBitmap: Bitmap? = null,
    val base64BitMap: String = "",
    val isLoading: Boolean = false,
    val showAiImageDialog: Boolean = false,
    val aiResponse: AzureAiResponse? = null,
)
