package com.ccspart2.pokeswap.presentation.routes.settings.ui

import android.app.LocaleManager
import android.os.Build
import android.os.LocaleList
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.os.LocaleListCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ccspart2.pokeswap.R
import com.ccspart2.pokeswap.presentation.core.ui.PreviewScreen
import com.ccspart2.pokeswap.presentation.routes.settings.viewmodel.SettingsEvent
import com.ccspart2.pokeswap.presentation.routes.settings.viewmodel.SettingsState
import com.ccspart2.pokeswap.presentation.routes.settings.viewmodel.SettingsViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

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
    var isLanguageClicked by remember { mutableStateOf(false) }
    var languageUsed by remember { mutableStateOf("en") }
    val context = LocalContext.current

    if (isLanguageClicked) {
        LaunchedEffect(key1 = Unit) {
            launch(Dispatchers.Main) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    context.getSystemService(LocaleManager::class.java).applicationLocales =
                        LocaleList.forLanguageTags(languageUsed)
                } else {
                    AppCompatDelegate.setApplicationLocales(
                        LocaleListCompat.forLanguageTags(
                            languageUsed,
                        ),
                    )
                }
            }
            isLanguageClicked = false
        }
    }

    LaunchedEffect(key1 = Unit) {
        launch(Dispatchers.Main) {
            val currentAppLocales = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                context.getSystemService(LocaleManager::class.java)?.applicationLocales.toString()
            } else {
                AppCompatDelegate.getApplicationLocales().toString()
            }
            languageUsed = currentAppLocales.replace("[", "").replace("]", "").trim()
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)
            .padding(20.dp),
    ) {
        Text(
            text = stringResource(id = R.string.settings_currency_title),
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

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = stringResource(id = R.string.settings_language_title),
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
                onClick = {
                    languageUsed = "en"
                    isLanguageClicked = true
                },
                modifier = Modifier.size(75.dp),
                border = BorderStroke(
                    width = 1.dp,
                    color = if (languageUsed == "en") Color.Blue else Color.Black,
                ),

            ) {
                Image(
                    painter = painterResource(id = R.drawable.english_language_logo),
                    contentDescription = "",
                    modifier = Modifier
                        .size(75.dp),
                )
            }
            OutlinedIconButton(
                shape = RoundedCornerShape(8.dp),
                onClick = {
                    languageUsed = "es"
                    isLanguageClicked = true
                },
                modifier = Modifier
                    .size(75.dp),
                border = BorderStroke(
                    width = 1.dp,
                    color = if (languageUsed == "es") Color.Blue else Color.Black,
                ),

            ) {
                Image(
                    painter = painterResource(id = R.drawable.spanish_language_logo),
                    contentDescription = "",
                    modifier = Modifier
                        .size(75.dp),
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
