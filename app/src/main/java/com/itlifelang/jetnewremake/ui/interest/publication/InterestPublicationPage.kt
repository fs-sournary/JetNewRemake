package com.itlifelang.jetnewremake.ui.interest.publication

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.itlifelang.jetnewremake.ui.component.AppError
import com.itlifelang.jetnewremake.ui.component.AppLoading
import com.itlifelang.jetnewremake.ui.interest.InterestTopicItem
import com.itlifelang.jetnewremake.ui.theme.AppTheme
import com.itlifelang.jetnewremake.ui.util.UiState
import com.itlifelang.jetnewremake.util.ThemePreview

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun InterestPublicationPage(
    viewModel: InterestPublicationViewModel = hiltViewModel(),
    isExpandScreen: Boolean
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    InterestPublicationPage(isExpandScreen = isExpandScreen, uiState = uiState) {
        viewModel.getPublications()
    }
}

@Composable
private fun InterestPublicationPage(
    modifier: Modifier = Modifier,
    isExpandScreen: Boolean,
    uiState: UiState<List<String>>,
    onReload: () -> Unit
) {
    when (uiState) {
        is UiState.Loading -> {
            AppLoading(modifier = modifier.fillMaxSize())
        }
        is UiState.Success<List<String>> -> {
            if (!isExpandScreen) {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(uiState.data, key = { it }) {
                        InterestTopicItem(itemTitle = it, selected = false) {}
                    }
                }
            } else {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(uiState.data, key = { it }) {
                        InterestTopicItem(itemTitle = it, selected = false) {}
                    }
                }
            }
        }
        is UiState.Error -> {
            AppError(modifier = modifier.fillMaxSize(), onReload = onReload)
        }
    }
}

@ThemePreview
@Composable
fun PreviewInterestPublicationPage() {
    val data = listOf(
        "Kotlin Vibe",
        "Compose Mix",
        "Compose Breakdown",
        "Android Pursue",
        "Kotlin Watchman",
        "Jetpack Ark",
        "Composeshack",
        "Jetpack Point",
        "Compose Tribune"
    )
    AppTheme {
        Surface {
            InterestPublicationPage(isExpandScreen = false, uiState = UiState.Success(data)) {}
        }
    }
}

@Preview(name = "tablet", group = "devices", device = Devices.TABLET)
@Composable
fun PreviewExpandInterestPublicationPage() {
    val data = listOf(
        "Kotlin Vibe",
        "Compose Mix",
        "Compose Breakdown",
        "Android Pursue",
        "Kotlin Watchman",
        "Jetpack Ark",
        "Composeshack",
        "Jetpack Point",
        "Compose Tribune"
    )
    AppTheme {
        Surface {
            InterestPublicationPage(isExpandScreen = true, uiState = UiState.Success(data)) {}
        }
    }
}
