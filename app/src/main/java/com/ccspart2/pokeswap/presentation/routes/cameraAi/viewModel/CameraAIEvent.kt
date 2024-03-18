package com.ccspart2.pokeswap.presentation.routes.cameraAi.viewModel

import android.graphics.Bitmap

sealed class CameraAIEvent {
    data class OnTakePicture(val bitmap: Bitmap) : CameraAIEvent()
    data object OnAiImageDialogDismiss : CameraAIEvent()
}
