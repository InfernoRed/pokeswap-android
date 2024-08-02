package com.ccspart2.pokeswap.presentation.routes.cameraAi.ui

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Matrix
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture.OnImageCapturedCallback
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.ImageProxy
import androidx.camera.view.CameraController
import androidx.camera.view.LifecycleCameraController
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cameraswitch
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ccspart2.pokeswap.R
import com.ccspart2.pokeswap.data.model.aIInfo.response.EbayAiResponse
import com.ccspart2.pokeswap.presentation.core.ui.PreviewScreen
import com.ccspart2.pokeswap.presentation.core.ui.components.FilledButton
import com.ccspart2.pokeswap.presentation.core.ui.components.LoadingDialog
import com.ccspart2.pokeswap.presentation.routes.cameraAi.ui.components.CameraPreview
import com.ccspart2.pokeswap.presentation.routes.cameraAi.viewModel.CameraAIEvent
import com.ccspart2.pokeswap.presentation.routes.cameraAi.viewModel.CameraAIState
import com.ccspart2.pokeswap.presentation.routes.cameraAi.viewModel.CameraAIViewModel
import com.ccspart2.pokeswap.presentation.routes.cameraAi.viewModel.CameraInternalState
import com.ccspart2.pokeswap.utils.LogUtils
import kotlinx.coroutines.flow.StateFlow

@Composable
fun CameraAIRoute(navController: NavController) {
  val viewModel = hiltViewModel<CameraAIViewModel>()
  CameraAIScreen(
    stateFlow = viewModel.viewState,
    onTakePhoto = { viewModel.handleEvent(CameraAIEvent.OnTakePicture(it)) },
    onNextImageButtonClicked = { viewModel.handleEvent(CameraAIEvent.OnNextImage) },
    onDismissRequest = { navController.popBackStack() },
  )
}

@Composable
private fun CameraAIScreen(
  stateFlow: StateFlow<CameraAIState>,
  onTakePhoto: (Bitmap) -> Unit,
  onNextImageButtonClicked: () -> Unit,
  onDismissRequest: () -> Unit,
) {
  val viewState by stateFlow.collectAsState()
  val context = LocalContext.current
  val controller = remember {
    LifecycleCameraController(context).apply { setEnabledUseCases(CameraController.IMAGE_CAPTURE) }
  }

  Box(modifier = Modifier.fillMaxSize()) {
    when (viewState.internalState) {
      CameraInternalState.LOADING -> LoadingScreen()
      CameraInternalState.CAMERA_PREVIEW ->
        CameraScreen(controller = controller, context = context, onTakePhoto = onTakePhoto)
      CameraInternalState.RESPONSE_RECEIVED ->
        ResponseScreen(
          photoBitmap = viewState.capturedBitmap,
          response = viewState.ebayAiResponse,
          onNextButtonClicked = onNextImageButtonClicked,
        )
      CameraInternalState.ERROR ->
        ErrorScreen(
          exception = viewState.exception,
          onConfirmButtonClicked = onNextImageButtonClicked,
          onDismissRequest = onDismissRequest,
        )
    }
  }
}

@Composable
private fun CameraScreen(
  controller: LifecycleCameraController,
  context: Context,
  onTakePhoto: (Bitmap) -> Unit,
) {
  Box(modifier = Modifier.fillMaxSize()) {
    CameraPreview(controller = controller, modifier = Modifier.fillMaxSize())
    Row(
      horizontalArrangement = Arrangement.SpaceAround,
      modifier = Modifier.fillMaxWidth().align(Alignment.BottomCenter).padding(16.dp),
    ) {
      IconButton(
        onClick = {
          controller.cameraSelector =
            if (controller.cameraSelector == CameraSelector.DEFAULT_BACK_CAMERA) {
              CameraSelector.DEFAULT_FRONT_CAMERA
            } else {
              CameraSelector.DEFAULT_BACK_CAMERA
            }
        }
      ) {
        Icon(imageVector = Icons.Default.Cameraswitch, contentDescription = "Switch Camera")
      }
      IconButton(
        onClick = {
          takePhoto(controller = controller, context = context, onPhotoTaken = { onTakePhoto(it) })
        }
      ) {
        Icon(imageVector = Icons.Default.PhotoCamera, contentDescription = "Take Photo")
      }
    }
  }
}

@Composable
private fun ResponseScreen(
  photoBitmap: Bitmap? = null,
  response: EbayAiResponse? = null,
  onNextButtonClicked: () -> Unit,
) {
  Column(
    modifier =
      Modifier.fillMaxSize()
        .background(MaterialTheme.colorScheme.primary)
        .verticalScroll(rememberScrollState()),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    Text(
      text = "Azure Open Ai Response for :",
      style = MaterialTheme.typography.titleLarge,
      textAlign = TextAlign.Start,
      modifier = Modifier.padding(vertical = 15.dp),
    )
    photoBitmap?.let { picture ->
      Image(
        bitmap = picture.asImageBitmap(),
        contentDescription = "Recently taken photo",
        modifier = Modifier.width(300.dp).height(400.dp),
      )
    }
    Text(
      text = response?.cardName ?: "",
      style = MaterialTheme.typography.titleLarge,
      textAlign = TextAlign.Start,
      modifier = Modifier.padding(vertical = 15.dp),
    )
    Text(
      text = response?.description ?: "",
      style = MaterialTheme.typography.bodyMedium,
      textAlign = TextAlign.Start,
      modifier = Modifier.padding(horizontal = 15.dp),
    )
    Column(
      modifier =
        Modifier.fillMaxWidth()
          .padding(horizontal = 15.dp, vertical = 15.dp)
          .border(
            width = 1.dp,
            color = MaterialTheme.colorScheme.outline,
            shape = RoundedCornerShape(10.dp),
          )
          .clip(RoundedCornerShape(10.dp))
          .background(MaterialTheme.colorScheme.background)
    ) {
      Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 15.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
      ) {
        Image(
          painter = painterResource(id = R.drawable.ebay_ar21),
          contentDescription = "",
          modifier = Modifier.height(50.dp),
        )
        Text(
          text = "Title Suggestion",
          style = MaterialTheme.typography.titleLarge,
          textAlign = TextAlign.Center,
          modifier = Modifier.padding(horizontal = 15.dp),
        )
      }
      Text(
        text = response?.ebayTitle ?: "",
        style = MaterialTheme.typography.bodyLarge,
        textAlign = TextAlign.Center,
        modifier = Modifier.padding(horizontal = 15.dp, vertical = 15.dp),
      )
    }
    FilledButton(onClick = onNextButtonClicked, modifier = Modifier.padding(bottom = 15.dp)) {
      Text(
        text = "Next Photo",
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(horizontal = 40.dp),
      )
    }
  }
}

@Composable
private fun ErrorScreen(
  exception: Exception?,
  onDismissRequest: () -> Unit,
  onConfirmButtonClicked: () -> Unit,
) {
  Column(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.primary)) {
    AlertDialog(
      icon = {
        Icon(painterResource(id = R.drawable.baseline_error_outline_24), contentDescription = "")
      },
      title = { Text(text = "Response Error") },
      text = {
        Text(
          text =
            "The current image cannot be processed. Please try Again. Exception: ${exception?.message}"
        )
      },
      onDismissRequest = { onDismissRequest() },
      confirmButton = { TextButton(onClick = { onConfirmButtonClicked() }) { Text("Try Again") } },
      dismissButton = { TextButton(onClick = { onDismissRequest() }) { Text("Go Back") } },
    )
  }
}

@Composable
private fun LoadingScreen() {
  Column(
    modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.primary),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    LoadingDialog()
  }
}

@Preview
@Composable
private fun ResponseScreenPreview() {
  PreviewScreen {
    ResponseScreen(
      photoBitmap =
        ImageBitmap.imageResource(id = R.drawable.pokemon_card_backside).asAndroidBitmap(),
      response = EbayAiResponse.mock(),
      onNextButtonClicked = {},
    )
  }
}

@Preview
@Composable
private fun LoadingScreenPreview() {
  PreviewScreen { LoadingScreen() }
}

@Preview
@Composable
private fun ErrorScreenPreview() {
  PreviewScreen {
    ErrorScreen(exception = null, onDismissRequest = {}, onConfirmButtonClicked = {})
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

        val matrix = Matrix().apply { postRotate(image.imageInfo.rotationDegrees.toFloat()) }

        val rotatedBitmap =
          Bitmap.createBitmap(
            /* source = */ image.toBitmap(),
            /* x = */ 0,
            /* y = */ 0,
            /* width = */ image.width,
            /* height = */ image.height,
            /* m = */ matrix,
            /* filter = */ false,
          )

        onPhotoTaken(rotatedBitmap)
      }

      override fun onError(exception: ImageCaptureException) {
        super.onError(exception)
        LogUtils.error("Camera Error: ${exception.message}")
      }
    },
  )
}
