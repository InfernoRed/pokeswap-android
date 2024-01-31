package com.ccspart2.pokeswap_android.presentation.core.ui.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ccspart2.pokeswap_android.presentation.core.ui.PreviewScreen

@Composable
fun FilledButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    contentPadding: PaddingValues = PaddingValues(vertical = 10.dp, horizontal = 24.dp),
    shape: Shape = MaterialTheme.shapes.extraLarge,
    colors: ButtonColors = ButtonDefaults.buttonColors(Color.Black),
    elevation: ButtonElevation? = ButtonDefaults.buttonElevation(),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable RowScope.() -> Unit,
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        shape = shape,
        colors = colors,
        contentPadding = contentPadding,
        elevation = elevation,
        interactionSource = interactionSource,
        content = content,
    )
}

@Preview
@Composable
private fun ButtonsPreview() {
    PreviewScreen {
        Column {
            FilledButton(onClick = {}) {
                Text(text = "Enabled")
            }
            FilledButton(onClick = {}, enabled = false) {
                Text(text = "Disabled")
            }
        }
    }
}
