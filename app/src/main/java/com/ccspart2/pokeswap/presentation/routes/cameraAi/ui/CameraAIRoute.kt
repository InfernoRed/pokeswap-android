package com.ccspart2.pokeswap.presentation.routes.cameraAi.ui

import android.content.Context
import android.graphics.Bitmap
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture.OnImageCapturedCallback
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.ImageProxy
import androidx.camera.view.CameraController
import androidx.camera.view.LifecycleCameraController
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cameraswitch
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.ccspart2.pokeswap.presentation.core.ui.PreviewScreen
import com.ccspart2.pokeswap.presentation.routes.cameraAi.ui.components.CameraPreview
import com.ccspart2.pokeswap.presentation.routes.cameraAi.viewModel.CameraAIEvent
import com.ccspart2.pokeswap.presentation.routes.cameraAi.viewModel.CameraAIState
import com.ccspart2.pokeswap.presentation.routes.cameraAi.viewModel.CameraAIViewModel
import com.ccspart2.pokeswap.utils.LogUtils
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun CameraAIRoute() {
    val viewModel = hiltViewModel<CameraAIViewModel>()
    CameraAIScreen(
        stateFlow = viewModel.viewState,
        onTakePhoto = {
            viewModel.handleEvent(CameraAIEvent.OnTakePicture(it))
        },
    )
}

@Composable
private fun CameraAIScreen(
    stateFlow: StateFlow<CameraAIState>,
    onTakePhoto: (Bitmap) -> Unit,
) {
    val viewState by stateFlow.collectAsState()
    val context = LocalContext.current
    val controller = remember {
        LifecycleCameraController(context).apply {
            setEnabledUseCases(CameraController.IMAGE_CAPTURE)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        CameraPreview(
            controller = controller,
            modifier = Modifier
                .fillMaxSize(),
        )
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(16.dp),
        ) {
            IconButton(
                onClick = {
                    controller.cameraSelector =
                        if (controller.cameraSelector == CameraSelector.DEFAULT_BACK_CAMERA) {
                            CameraSelector.DEFAULT_FRONT_CAMERA
                        } else {
                            CameraSelector.DEFAULT_BACK_CAMERA
                        }
                },
            ) {
                Icon(
                    imageVector = Icons.Default.Cameraswitch,
                    contentDescription = "Switch Camera",
                )
            }
            IconButton(
                onClick = {
                    takePhoto(
                        controller = controller,
                        context = context,
                        onPhotoTaken = {
                            onTakePhoto(it)
                        },
                    )
                },
            ) {
                Icon(
                    imageVector = Icons.Default.PhotoCamera,
                    contentDescription = "Take Photo",
                )
            }
        }
    }
}

private fun takePhoto(
    controller: LifecycleCameraController,
    onPhotoTaken: (Bitmap) -> Unit,
    context: Context,
) {
    controller.takePicture(
        ContextCompat.getMainExecutor(context),
        object : OnImageCapturedCallback() {
            override fun onCaptureSuccess(image: ImageProxy) {
                super.onCaptureSuccess(image)
                onPhotoTaken(image.toBitmap())
            }

            override fun onError(exception: ImageCaptureException) {
                super.onError(exception)
                LogUtils.error("Camera Error: ${exception.message}")
            }
        },
    )
}

@Preview
@Composable
private fun CameraAIPreview() {
    PreviewScreen {
        CameraAIScreen(
            stateFlow = MutableStateFlow(
                CameraAIState(name = "CameraAI"),
            ),
            onTakePhoto = {},
        )
    }
}
