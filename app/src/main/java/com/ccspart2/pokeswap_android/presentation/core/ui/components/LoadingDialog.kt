package com.ccspart2.pokeswap_android.presentation.core.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ccspart2.pokeswap_android.R
import com.ccspart2.pokeswap_android.ui.theme.PokeSwapAndroidTheme

@Composable
fun LoadingDialog(
    modifier: Modifier = Modifier,
    @StringRes label: Int = R.string.loading_component_label,
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .width(112.dp)
            .height(110.dp)
            .background(MaterialTheme.colorScheme.inverseSurface.copy(alpha = 0.87F)),
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            CircularProgressIndicator(
                color = MaterialTheme.colorScheme.onPrimary,
            )
            Text(
                text = stringResource(id = label),
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .padding(vertical = 8.dp),
            )
        }
    }
}

@Preview
@Composable
private fun LoadingDialogPreview() {
    PokeSwapAndroidTheme {
        LoadingDialog()
    }
}
