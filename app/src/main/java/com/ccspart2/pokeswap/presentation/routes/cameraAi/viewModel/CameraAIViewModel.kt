package com.ccspart2.pokeswap.presentation.routes.cameraAi.viewModel

import android.graphics.Bitmap
import android.util.Base64
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ccspart2.pokeswap.data.model.aIInfo.request.AzureAiRequest
import com.ccspart2.pokeswap.data.model.aIInfo.request.Message
import com.ccspart2.pokeswap.network.repo.AzureAiRepository
import com.ccspart2.pokeswap.utils.LogUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream
import javax.inject.Inject

@HiltViewModel
class CameraAIViewModel @Inject constructor(
    private val azureAiRepository: AzureAiRepository,
) : ViewModel() {
    private val _viewState = MutableStateFlow(CameraAIState())
    val viewState = _viewState.asStateFlow()

    fun handleEvent(event: CameraAIEvent) {
        when (event) {
            is CameraAIEvent.OnTakePicture -> onTakePhoto(event.bitmap)
            is CameraAIEvent.OnAiImageDialogDismiss -> dismissImageDialog()
        }
    }

    private fun dismissImageDialog() {
        _viewState.update { state ->
            state.copy(
                showAiImageDialog = false,
            )
        }
    }

    private fun onTakePhoto(bitmap: Bitmap) {
        val base64String = encodeImage(bitmap)
        _viewState.update { state ->
            state.copy(
                capturedBitmap = bitmap,
                base64BitMap = base64String ?: "",
                showAiImageDialog = true,
                isLoading = true,
            )
        }

        val azureAiRequest = AzureAiRequest(
            maxTokens = 800,
            messages = listOf(
                Message.getSystemMessage(),
                Message.getUserMessageWithImage(base64String),
            ),
            temperature = 0.5,
            topP = 0.95,
            stream = false,
        )
        viewModelScope.launch {
            azureAiRepository.getCardAiInfo(azureAiRequest).collect { response ->
                LogUtils.info(response.toString())
                _viewState.update { state ->
                    state.copy(
                        isLoading = false,
                        aiResponse = response,
                    )
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
