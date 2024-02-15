package com.ccspart2.pokeswap.presentation.routes.cardDetails.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ccspart2.pokeswap.presentation.core.ui.PreviewScreen

@Composable
fun CardDetailsSectionItem(
    sectionLabel: String,
    valueLabel: String,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.scrim),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(3.dp)
                .background(MaterialTheme.colorScheme.primary),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = "$sectionLabel:",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier
                    .padding(horizontal = 10.dp),
            )
            Text(
                text = valueLabel,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onPrimary,
                textAlign = TextAlign.End,
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .width(100.dp),
            )
        }
    }
}

@Preview
@Composable
private fun CardDetailsSectionItemPreview() {
    PreviewScreen {
        CardDetailsSectionItem(
            "Rarity",
            "100.00",
        )
    }
}
