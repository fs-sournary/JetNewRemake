package com.itlifelang.jetnewremake.ui.interest.topic

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
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
import com.itlifelang.jetnewremake.ui.interest.InterestTopicSeparator
import com.itlifelang.jetnewremake.ui.theme.AppTheme
import com.itlifelang.jetnewremake.ui.util.UiState
import com.itlifelang.jetnewremake.util.CompletePreview

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun InterestTopicPage(
    viewModel: InterestTopicViewModel = hiltViewModel(),
    isExpandScreen: Boolean
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    InterestTopicPage(isExpandScreen = isExpandScreen, uiState = uiState) {
        viewModel.getTopicSections()
    }
}

@Composable
private fun InterestTopicPage(
    modifier: Modifier = Modifier,
    isExpandScreen: Boolean,
    uiState: InterestTopicUiState,
    onReload: () -> Unit
) {
    when (val topicState = uiState.topicState) {
        is UiState.Loading -> {
            AppLoading(modifier = modifier.fillMaxSize())
        }
        is UiState.Success<List<TopicUi>> -> {
            if (!isExpandScreen) {
                LazyColumn(modifier = modifier.fillMaxSize()) {
                    items(topicState.data) {
                        when (it) {
                            is TopicUi.Item -> {
                                InterestTopicItem(
                                    itemTitle = it.interest,
                                    selected = false,
                                    onToggle = { /*TODO*/ },
                                )
                            }
                            is TopicUi.Separator -> {
                                InterestTopicSeparator(title = it.title)
                            }
                        }
                    }
                }
            } else {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = modifier.fillMaxSize()
                ) {
                    items(
                        items = topicState.data,
                        span = { GridItemSpan(if (it is TopicUi.Separator) maxLineSpan else 1) }
                    ) {
                        when (it) {
                            is TopicUi.Item -> {
                                InterestTopicItem(
                                    itemTitle = it.interest,
                                    selected = false,
                                    onToggle = { /*TODO*/ },
                                )
                            }
                            is TopicUi.Separator -> {
                                InterestTopicSeparator(title = it.title)
                            }
                        }
                    }
                }
            }
        }
        is UiState.Error -> {
            AppError(modifier = modifier.fillMaxSize(), onReload = onReload)
        }
    }
}

@CompletePreview
@Composable
fun PreviewInterestTopicPage() {
    val topicUis = listOf(
        TopicUi.Separator("Android"),
        TopicUi.Item("Android", "Jetpack Compose"),
        TopicUi.Item("Android", "Kotlin"),
        TopicUi.Item("Android", "Jetpack"),
        TopicUi.Separator("Programming"),
        TopicUi.Item("Programming", "Kotlin"),
        TopicUi.Item("Programming", "Declarative UIs"),
        TopicUi.Item("Programming", "Java"),
        TopicUi.Item("Programming", "Unidirectional Data Flow"),
    )
    AppTheme {
        Surface {
            InterestTopicPage(
                isExpandScreen = false,
                uiState = InterestTopicUiState(UiState.Success(topicUis))
            ) {}
        }
    }
}

@Preview(name = "tablet", group = "devices", device = Devices.TABLET)
@Composable
fun PreviewExpandInterestTopicPage() {
    val topicUis = listOf(
        TopicUi.Separator("Android"),
        TopicUi.Item("Android", "Jetpack Compose"),
        TopicUi.Item("Android", "Kotlin"),
        TopicUi.Item("Android", "Jetpack"),
        TopicUi.Separator("Programming"),
        TopicUi.Item("Programming", "Kotlin"),
        TopicUi.Item("Programming", "Declarative UIs"),
        TopicUi.Item("Programming", "Java"),
        TopicUi.Item("Programming", "Unidirectional Data Flow"),
    )
    AppTheme {
        Surface {
            InterestTopicPage(
                isExpandScreen = true,
                uiState = InterestTopicUiState(UiState.Success(topicUis))
            ) {}
        }
    }
}
