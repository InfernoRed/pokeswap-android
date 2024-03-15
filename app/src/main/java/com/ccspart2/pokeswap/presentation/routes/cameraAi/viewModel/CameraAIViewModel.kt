package com.ccspart2.pokeswap.presentation.routes.cameraAi.viewModel

import android.graphics.Bitmap
import android.util.Base64
import androidx.lifecycle.ViewModel
import com.ccspart2.pokeswap.utils.LogUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.io.ByteArrayOutputStream
import javax.inject.Inject

@HiltViewModel
class CameraAIViewModel @Inject constructor() : ViewModel() {
    private val _viewState = MutableStateFlow(CameraAIState())
    val viewState = _viewState.asStateFlow()

    fun handleEvent(event: CameraAIEvent) {
        when (event) {
            is CameraAIEvent.OnTakePicture -> onTakePhoto(event.bitmap)
        }
    }

    private fun onTakePhoto(bitmap: Bitmap) {
        val base64String = encodeImage(bitmap)
        LogUtils.info("base == $base64String")

        _viewState.update { state ->
            state.copy(
                capturedBitmap = bitmap,
                base64BitMap = base64String ?: "",
            )
        }
    }

    private fun encodeImage(bm: Bitmap): String? {
        val byteArrayOutputStream = ByteArrayOutputStream()
        bm.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
        val b = byteArrayOutputStream.toByteArray()
        return Base64.encodeToString(b, Base64.DEFAULT)
    }
}
