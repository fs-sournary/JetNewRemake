package com.itlifelang.jetnewremake.ui.home

import android.os.Parcelable
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.itlifelang.jetnewremake.model.PostsFeed
import com.itlifelang.jetnewremake.ui.component.AppError
import com.itlifelang.jetnewremake.ui.component.AppLoading
import com.itlifelang.jetnewremake.ui.navigation.ArticleNavGraph
import com.itlifelang.jetnewremake.ui.util.UiState
import kotlinx.parcelize.Parcelize

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLifecycleComposeApi::class)
@Composable
fun HomeLargeScreen(
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    var postArgs by rememberSaveable { mutableStateOf(PostArgument()) }
    val homeUiState by homeViewModel.uiState.collectAsStateWithLifecycle()
    Scaffold { innerPadding ->
        when (homeUiState.postsFeedState) {
            is UiState.Loading -> {
                AppLoading(modifier = Modifier.fillMaxSize())
            }
            is UiState.Success<PostsFeed> -> {
                Row(modifier = Modifier.padding(innerPadding)) {
                    HomeContent(
                        modifier = Modifier.width(334.dp),
                        postsFeedState = homeUiState.postsFeedState,
                        onLoadPostSuccess = {
                            postArgs = PostArgument(it.id, it.publication?.name.orEmpty())
                        },
                        isExpandScreen = true,
                        searchInput = homeUiState.searchInput,
                        showClearSearch = homeUiState.searchInput.isNotEmpty(),
                        onSearchInputChange = { homeViewModel.changeSearchInput(it) },
                        onClearSearchClick = { homeViewModel.changeSearchInput("") },
                        onRefreshPosts = { homeViewModel.getPostsFeed() },
                        onReloadPosts = { homeViewModel.getPostsFeed() },
                        onHighlightPostItemClick = {
                            postArgs = PostArgument(it.id, it.publication?.name.orEmpty())
                        },
                        onRecommendPostItemClick = {
                            postArgs = PostArgument(it.id, it.publication?.name.orEmpty())
                        },
                        onRecommendPostBookmarkChange = { _, _ -> },
                        onPopularPostItemClick = {
                            postArgs = PostArgument(it.id, it.publication?.name.orEmpty())
                        },
                        onRecentPostItemClick = {
                            postArgs = PostArgument(it.id, it.publication?.name.orEmpty())
                        },
                        onMoreActionRecentPostItemClick = {
                            postArgs = PostArgument(it.id, it.publication?.name.orEmpty())
                        }
                    )
                    if (postArgs == PostArgument()) {
                        AppLoading(modifier = Modifier.fillMaxSize())
                    } else {
                        Crossfade(targetState = postArgs) {
                            ArticleNavGraph(
                                postId = it.postId,
                                publicationName = it.publicationName
                            )
                        }
                    }
                }
            }
            is UiState.Error -> {
                AppError(modifier = Modifier.fillMaxSize()) {
                    homeViewModel.getPostsFeed()
                }
            }
        }
    }
}

@Parcelize
data class PostArgument(val postId: String = "", val publicationName: String = "") : Parcelable
