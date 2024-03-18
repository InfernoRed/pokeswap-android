package com.ccspart2.pokeswap.presentation.routes.cameraAi.ui.components

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.ccspart2.pokeswap.data.model.aIInfo.response.AzureAiResponse
import com.ccspart2.pokeswap.presentation.core.ui.PreviewScreen
import com.ccspart2.pokeswap.presentation.core.ui.components.FilledButton
import com.ccspart2.pokeswap.presentation.core.ui.components.LoadingDialog

@Composable
fun AiCardResponseDialog(
    response: AzureAiResponse?,
    isLoading: Boolean,
    photoBitmap: Bitmap? = null,
    onClose: () -> Unit,
) {
    Dialog(onDismissRequest = onClose) {
        Surface(
            shape = RoundedCornerShape(16.dp),
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(700.dp)
                    .background(MaterialTheme.colorScheme.primary),
            ) {
                if (isLoading) {
                    LoadingDialog()
                } else {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxSize(),
                    ) {
                        Text(
                            text = "Azure Open Ai Response for :",
                            style = MaterialTheme.typography.titleMedium,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .padding(vertical = 15.dp),
                        )
                        if (photoBitmap != null) {
                            Image(
                                bitmap = photoBitmap.asImageBitmap(),
                                contentDescription = "Recently taken photo",
                                modifier = Modifier
                                    .size(200.dp),
                            )
                        }
                        response?.let {
                            Text(
                                text = response.choices[0].message.content,
                                style = MaterialTheme.typography.titleMedium,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .padding(vertical = 15.dp),
                            )
                        }
                        FilledButton(
                            onClick = onClose,
                        ) {
                            Text(
                                text = "Close",
                                modifier = Modifier
                                    .padding(horizontal = 20.dp),
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun AiCardResponseDialogPreview() {
    PreviewScreen {
        AiCardResponseDialog(
            response = null,
            onClose = {},
            isLoading = false,
            photoBitmap = null,
        )
    }
}
