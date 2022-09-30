package com.itlifelang.jetnewremake.ui.article

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.itlifelang.jetnewremake.R

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun ArticleContent(viewModel: ArticleViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    ArticleContent(uiState = uiState) { viewModel.getPost() }
}

@Composable
fun ArticleContent(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    uiState: ArticleUiState,
    onReloadPost: () -> Unit
) {
    when (uiState.postState) {
        is ArticlePostState.Loading -> {
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .padding(contentPadding),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        is ArticlePostState.Success -> {
            val post = uiState.postState.post
            LazyColumn(modifier = modifier.padding(contentPadding)) {
                item {
                    ArticleHeader(
                        modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp),
                        post = post
                    )
                }
                item {
                    ArticlePostMetadata(
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                        post = post
                    )
                }
                items(post.paragraphs) {
                    ArticlePostParagraph(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        paragraph = it
                    )
                }
                item {
                    Spacer(modifier = Modifier.height(24.dp))
                }
            }
        }
        is ArticlePostState.Error -> {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(contentPadding),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(text = stringResource(id = R.string.common_error))
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedButton(onClick = onReloadPost) {
                    Text(text = stringResource(id = R.string.reload))
                }
            }
        }
    }
}
