package com.ccspart2.pokeswap.presentation.routes.cameraAi.viewModel

import android.graphics.Bitmap
import android.util.Base64
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ccspart2.pokeswap.data.model.aIInfo.request.AzureAiRequest
import com.ccspart2.pokeswap.data.model.aIInfo.request.Message
import com.ccspart2.pokeswap.data.model.aIInfo.response.EbayAiResponse
import com.ccspart2.pokeswap.network.repo.AzureAiRepository
import com.ccspart2.pokeswap.utils.LogUtils
import com.ccspart2.pokeswap.utils.StringUtils
import com.squareup.moshi.Moshi
import dagger.hilt.android.lifecycle.HiltViewModel
import java.io.ByteArrayOutputStream
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class CameraAIViewModel
@Inject
constructor(private val azureAiRepository: AzureAiRepository, private val moshi: Moshi) :
  ViewModel() {

  private val _viewState = MutableStateFlow(CameraAIState())
  val viewState = _viewState.asStateFlow()

  fun handleEvent(event: CameraAIEvent) {
    when (event) {
      is CameraAIEvent.OnTakePicture -> onTakePhoto(event.bitmap)
      CameraAIEvent.OnNextImage -> clearImageResponse()
    }
  }

  private fun clearImageResponse() {
    _viewState.update { state ->
      state.copy(
        capturedBitmap = null,
        base64BitMap = "",
        internalState = CameraInternalState.CAMERA_PREVIEW,
      )
    }
  }

  private fun onTakePhoto(bitmap: Bitmap) {
    val base64String = encodeImage(bitmap)
    _viewState.update { state ->
      state.copy(
        capturedBitmap = bitmap,
        base64BitMap = base64String ?: "",
        internalState = CameraInternalState.LOADING,
      )
    }

    val azureAiRequest =
      AzureAiRequest(
        maxTokens = 800,
        messages =
          listOf(Message.getSystemMessage(), Message.getUserMessageWithImage(base64String)),
        temperature = 0.5,
        topP = 0.95,
        stream = false,
      )
    viewModelScope.launch {
      azureAiRepository.getCardAiInfo(azureAiRequest).collect { response ->
        if (response != null) {

          try {
            LogUtils.info(response.choices[0].message.content)

            val jsonResponse = StringUtils.extractJsonString(response.choices[0].message.content)
            LogUtils.info(jsonResponse)

            val jsonAdapter = moshi.adapter(EbayAiResponse::class.java)
            val ebayAiResponse = jsonAdapter.fromJson(jsonResponse)

            LogUtils.info(ebayAiResponse.toString())
            _viewState.update { state ->
              state.copy(
                internalState = CameraInternalState.RESPONSE_RECEIVED,
                aiResponse = response,
                ebayAiResponse = ebayAiResponse,
              )
            }
          } catch (e: Exception) {
            _viewState.update { state ->
              state.copy(internalState = CameraInternalState.ERROR, exception = e)
            }
          }
        }
      }
    }
  }

  private fun encodeImage(bm: Bitmap): String? {
    val byteArrayOutputStream = ByteArrayOutputStream()
    bm.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
    val b = byteArrayOutputStream.toByteArray()
    return Base64.encodeToString(b, Base64.DEFAULT)
  }
}
