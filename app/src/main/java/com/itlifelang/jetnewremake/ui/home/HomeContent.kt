package com.itlifelang.jetnewremake.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.itlifelang.jetnewremake.R
import com.itlifelang.jetnewremake.model.Post
import com.itlifelang.jetnewremake.model.PostsFeed
import com.itlifelang.jetnewremake.ui.component.AppError
import com.itlifelang.jetnewremake.ui.component.AppLoading
import com.itlifelang.jetnewremake.ui.util.UiState

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun HomeContent(
    homeViewModel: HomeViewModel = hiltViewModel(),
    modifier: Modifier,
    onLoadPostSuccess: (Post) -> Unit,
    onHighlightPostItemClick: (Post) -> Unit,
    onRecommendPostItemClick: (Post) -> Unit,
    onRecommendPostBookmarkChange: (Post, Boolean) -> Unit,
    onPopularPostItemClick: (Post) -> Unit,
    onRecentPostItemClick: (Post) -> Unit,
    onMoreActionRecentPostItemClick: (Post) -> Unit
) {
    val homeUiState by homeViewModel.uiState.collectAsStateWithLifecycle()
    HomeContent(
        modifier = modifier,
        postsFeedState = homeUiState.postsFeedState,
        onLoadPostSuccess = onLoadPostSuccess,
        isExpandScreen = true,
        searchInput = homeUiState.searchInput,
        showClearSearch = homeUiState.searchInput.isNotEmpty(),
        onSearchInputChange = { homeViewModel.changeSearchInput(it) },
        onClearSearchClick = { homeViewModel.changeSearchInput("") },
        onRefreshPosts = { homeViewModel.getPostsFeed() },
        onReloadPosts = { homeViewModel.getPostsFeed() },
        onHighlightPostItemClick = onHighlightPostItemClick,
        onRecommendPostItemClick = onRecommendPostItemClick,
        onRecommendPostBookmarkChange = onRecommendPostBookmarkChange,
        onPopularPostItemClick = onPopularPostItemClick,
        onRecentPostItemClick = onRecentPostItemClick,
        onMoreActionRecentPostItemClick = onMoreActionRecentPostItemClick
    )
}

@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    postListState: LazyListState = rememberLazyListState(),
    onLoadPostSuccess: (Post) -> Unit,
    postsFeedState: UiState<PostsFeed>,
    onRefreshPosts: () -> Unit,
    isExpandScreen: Boolean,
    searchInput: String,
    onSearchInputChange: (String) -> Unit,
    showClearSearch: Boolean,
    onClearSearchClick: () -> Unit,
    onReloadPosts: () -> Unit,
    onHighlightPostItemClick: (Post) -> Unit,
    onRecommendPostItemClick: (Post) -> Unit,
    onRecommendPostBookmarkChange: (Post, Boolean) -> Unit,
    onPopularPostItemClick: (Post) -> Unit,
    onRecentPostItemClick: (Post) -> Unit,
    onMoreActionRecentPostItemClick: (Post) -> Unit
) {
    when (postsFeedState) {
        is UiState.Loading -> {
            AppLoading(
                modifier = modifier
                    .fillMaxSize()
                    .padding(contentPadding)
            )
        }
        is UiState.Success<PostsFeed> -> {
            val postsFeed = postsFeedState.data
            if (postsFeed.allPosts.isEmpty()) {
                Box(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(contentPadding),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = stringResource(id = R.string.empty_data))
                }
            } else {
                LaunchedEffect(key1 = true) {
                    onLoadPostSuccess(postsFeed.allPosts[0])
                }
                SwipeRefresh(
                    state = rememberSwipeRefreshState(isRefreshing = false),
                    onRefresh = onRefreshPosts,
                    swipeEnabled = !isExpandScreen
                ) {
                    HomePostList(
                        modifier = modifier,
                        contentPadding = contentPadding,
                        state = postListState,
                        showExpandedSearch = isExpandScreen,
                        searchInput = searchInput,
                        onSearchInputChange = onSearchInputChange,
                        showClearSearch = showClearSearch,
                        onClearSearchClick = onClearSearchClick,
                        postsFeed = postsFeed,
                        onHighlightPostItemClick = onHighlightPostItemClick,
                        onRecommendPostItemClick = onRecommendPostItemClick,
                        onRecommendPostBookmarkChange = onRecommendPostBookmarkChange,
                        onPopularPostItemClick = onPopularPostItemClick,
                        onRecentPostItemClick = onRecentPostItemClick,
                        onMoreActionRecentPostItemClick = onMoreActionRecentPostItemClick
                    )

                }
            }
        }
        is UiState.Error -> {
            AppError(
                modifier = modifier
                    .fillMaxSize()
                    .padding(contentPadding),
                onReload = onReloadPosts
            )
        }
    }
}
