package com.itlifelang.jetnewremake.ui.article

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.ThumbUpOffAlt
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.itlifelang.jetnewremake.R
import com.itlifelang.jetnewremake.data.post1
import com.itlifelang.jetnewremake.ui.theme.AppTheme
import com.itlifelang.jetnewremake.util.CompletePreview

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun ArticleScreen(
    articleViewModel: ArticleViewModel = hiltViewModel(),
    isExpandScreen: Boolean,
    onBack: () -> Unit
) {
    val uiState by articleViewModel.uiState.collectAsStateWithLifecycle()
    ArticleScreen(uiState = uiState, isExpandScreen = isExpandScreen, onBack = onBack) {
        articleViewModel.getPost()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ArticleScreen(
    uiState: ArticleUiState,
    isExpandScreen: Boolean,
    onBack: () -> Unit,
    onReloadPost: () -> Unit
) {
    val topAppBarState = rememberTopAppBarState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(topAppBarState)
    var showChangeTextDialog by rememberSaveable { mutableStateOf(false) }
    if (showChangeTextDialog) {
        ArticleTextSettingDialog { showChangeTextDialog = false }
    }
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(id = R.drawable.icon_article_background),
                            contentDescription = null,
                            modifier = Modifier
                                .clip(CircleShape)
                                .size(36.dp)
                        )
                        Text(
                            text = stringResource(
                                id = R.string.published_in,
                                formatArgs = arrayOf(uiState.publicationName)
                            ),
                            style = MaterialTheme.typography.labelLarge,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                },
                navigationIcon = {
                    if (!isExpandScreen) {
                        IconButton(onClick = onBack) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = stringResource(id = R.string.cd_back)
                            )
                        }
                    }
                },
                scrollBehavior = scrollBehavior
            )
        },
        bottomBar = {
            if (!isExpandScreen) {
                BottomAppBar {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Filled.ThumbUpOffAlt, contentDescription = null)
                    }
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Default.BookmarkBorder, contentDescription = null)
                    }
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Default.Share, contentDescription = null)
                    }
                    IconButton(
                        onClick = { showChangeTextDialog = true }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_text_settings),
                            contentDescription = null
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        ArticleContent(
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            contentPadding = innerPadding,
            uiState = uiState,
            onReloadPost = onReloadPost
        )
    }
}

@CompletePreview
@Composable
fun PreviewArticleScreen() {
    AppTheme {
        ArticleScreen(
            uiState = ArticleUiState(postState = ArticlePostState.Success(post = post1)),
            isExpandScreen = false,
            onBack = {},
            onReloadPost = {}
        )
    }
}
