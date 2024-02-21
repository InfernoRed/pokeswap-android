package com.ccspart2.pokeswap.presentation.routes.settings.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ccspart2.pokeswap.R
import com.ccspart2.pokeswap.presentation.core.ui.PreviewScreen
import com.ccspart2.pokeswap.presentation.routes.settings.viewmodel.SettingsEvent
import com.ccspart2.pokeswap.presentation.routes.settings.viewmodel.SettingsState
import com.ccspart2.pokeswap.presentation.routes.settings.viewmodel.SettingsViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun SettingsRoute(
    navController: NavController,
) {
    val viewModel = hiltViewModel<SettingsViewModel>()
    SettingsScreen(
        stateFlow = viewModel.viewState,
        onCurrencyToggle = {
            viewModel.handleEvents(SettingsEvent.ToggleCurrency(it))
        },
    )
}

@Composable
private fun SettingsScreen(
    stateFlow: StateFlow<SettingsState>,
    onCurrencyToggle: (Boolean) -> Unit,
) {
    val viewState by stateFlow.collectAsState()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)
            .padding(20.dp),
    ) {
        Spacer(modifier = Modifier.padding(50.dp))
        Text(
            text = "Choose the default currency",
            style = MaterialTheme.typography.titleLarge,
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(
                20.dp,
                alignment = Alignment.CenterHorizontally,
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
        ) {
            OutlinedIconButton(
                shape = RoundedCornerShape(8.dp),
                onClick = { onCurrencyToggle(true) },
                modifier = Modifier.size(75.dp),
                border = BorderStroke(
                    width = 1.dp,
                    color = if (viewState.isEuroSelected) Color.Blue else Color.Black,
                ),

            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_euro_24),
                    contentDescription = "",
                    modifier = Modifier
                        .size(75.dp),
                    tint = if (viewState.isEuroSelected) Color.Blue else Color.Black,
                )
            }
            OutlinedIconButton(
                shape = RoundedCornerShape(8.dp),
                onClick = { onCurrencyToggle(false) },
                modifier = Modifier.size(75.dp),
                border = BorderStroke(
                    width = 1.dp,
                    color = if (viewState.isEuroSelected) Color.Black else Color.Blue,
                ),

            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_usd_24),
                    contentDescription = "",
                    modifier = Modifier
                        .size(75.dp),
                    tint = if (viewState.isEuroSelected) Color.Black else Color.Blue,
                )
            }
        }
    }
}

@Preview
@Composable
private fun SettingsPreview() {
    PreviewScreen {
        SettingsScreen(
            stateFlow = MutableStateFlow(
                SettingsState(),
            ),
            onCurrencyToggle = {},
        )
    }
}
