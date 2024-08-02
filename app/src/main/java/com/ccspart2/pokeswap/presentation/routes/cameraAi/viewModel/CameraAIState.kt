package com.ccspart2.pokeswap.presentation.routes.cameraAi.viewModel

import android.graphics.Bitmap
import com.ccspart2.pokeswap.data.model.aIInfo.response.AzureAiResponse
import com.ccspart2.pokeswap.data.model.aIInfo.response.EbayAiResponse

data class CameraAIState(
  val capturedBitmap: Bitmap? = null,
  val base64BitMap: String = "",
  val aiResponse: AzureAiResponse? = null,
  val internalState: CameraInternalState = CameraInternalState.CAMERA_PREVIEW,
  val ebayAiResponse: EbayAiResponse? = null,
  val exception: Exception? = null,
)

enum class CameraInternalState {
  LOADING,
  CAMERA_PREVIEW,
  RESPONSE_RECEIVED,
  ERROR,
}
