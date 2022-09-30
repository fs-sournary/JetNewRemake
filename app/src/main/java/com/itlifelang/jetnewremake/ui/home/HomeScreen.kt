package com.itlifelang.jetnewremake.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.itlifelang.jetnewremake.R
import com.itlifelang.jetnewremake.data.posts
import com.itlifelang.jetnewremake.model.Post
import com.itlifelang.jetnewremake.ui.theme.AppTheme
import com.itlifelang.jetnewremake.ui.util.UiState
import com.itlifelang.jetnewremake.util.CompletePreview
import kotlinx.coroutines.launch

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel(),
    onOpenDrawer: () -> Unit,
    onNavigateToArticle: (Post) -> Unit
) {
    val uiState by homeViewModel.uiState.collectAsStateWithLifecycle()
    HomeScreen(
        uiState = uiState,
        onOpenDrawer = onOpenDrawer,
        onSearchInputChange = { homeViewModel.changeSearchInput(it) },
        onClearSearchClick = { homeViewModel.changeSearchInput("") },
        onRefreshPosts = { homeViewModel.getPostsFeed() },
        onReloadPosts = { homeViewModel.getPostsFeed() },
        onHighlightPostItemClick = { onNavigateToArticle(it) },
        onRecommendPostItemClick = { onNavigateToArticle(it) },
        onRecommendPostBookmarkChange = { _, _ -> },
        onPopularPostItemClick = { onNavigateToArticle(it) },
        onRecentPostItemClick = { onNavigateToArticle(it) },
        onMoreActionRecentPostItemClick = {}
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeScreen(
    uiState: HomeUiState,
    onOpenDrawer: () -> Unit,
    onSearchInputChange: (String) -> Unit,
    onClearSearchClick: () -> Unit,
    onRefreshPosts: () -> Unit,
    onReloadPosts: () -> Unit,
    onHighlightPostItemClick: (Post) -> Unit,
    onRecommendPostItemClick: (Post) -> Unit,
    onRecommendPostBookmarkChange: (Post, Boolean) -> Unit,
    onPopularPostItemClick: (Post) -> Unit,
    onRecentPostItemClick: (Post) -> Unit,
    onMoreActionRecentPostItemClick: (Post) -> Unit
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val topAppBarState = rememberTopAppBarState()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(topAppBarState)
    val postListState = rememberLazyListState()
    val showScrollToTop by remember {
        derivedStateOf { postListState.firstVisibleItemIndex > 0 }
    }
    val scope = rememberCoroutineScope()
    if (uiState.postsFeedState is UiState.Error) {
        LaunchedEffect(key1 = snackbarHostState) {
            snackbarHostState.showSnackbar("There was an error occur")
        }
    }
    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Image(
                        painter = painterResource(id = R.drawable.ic_jetnews_wordmark),
                        contentDescription = stringResource(id = R.string.home_title),
                        modifier = Modifier.fillMaxWidth(),
                        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground)
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onOpenDrawer) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_jetnews_logo),
                            contentDescription = stringResource(id = R.string.home_open_drawer_navigation)
                        )
                    }
                },
                actions = {
                    IconButton(
                        onClick = { /*TODO*/ }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = stringResource(id = R.string.search)
                        )
                    }
                },
                scrollBehavior = scrollBehavior
            )
        },
        floatingActionButton = {
            if (showScrollToTop) {
                FloatingActionButton(
                    onClick = {
                        scope.launch { postListState.animateScrollToItem(0) }
                    }
                ) {
                    Icon(imageVector = Icons.Filled.ArrowUpward, contentDescription = null)
                }
            }
        }
    ) { innerPadding ->
        HomeContent(
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            contentPadding = innerPadding,
            postListState = postListState,
            postsFeedState = uiState.postsFeedState,
            onLoadPostSuccess = {},
            onRefreshPosts = onRefreshPosts,
            isExpandScreen = false,
            searchInput = uiState.searchInput,
            onSearchInputChange = onSearchInputChange,
            showClearSearch = uiState.searchInput.isNotEmpty(),
            onClearSearchClick = onClearSearchClick,
            onReloadPosts = onReloadPosts,
            onHighlightPostItemClick = onHighlightPostItemClick,
            onRecommendPostItemClick = onRecommendPostItemClick,
            onRecommendPostBookmarkChange = onRecommendPostBookmarkChange,
            onPopularPostItemClick = onPopularPostItemClick,
            onRecentPostItemClick = onRecentPostItemClick,
            onMoreActionRecentPostItemClick = onMoreActionRecentPostItemClick
        )
    }
}

@CompletePreview
@Composable
fun PreviewHomeScreen() {
    AppTheme {
        Surface {
            HomeScreen(
                uiState = HomeUiState(postsFeedState = UiState.Success(posts), searchInput = ""),
                onOpenDrawer = {},
                onSearchInputChange = {},
                onClearSearchClick = {},
                onRefreshPosts = {},
                onReloadPosts = {},
                onHighlightPostItemClick = {},
                onRecommendPostItemClick = {},
                onRecommendPostBookmarkChange = { _, _ -> },
                onPopularPostItemClick = {},
                onRecentPostItemClick = {},
                onMoreActionRecentPostItemClick = {}
            )
        }
    }
}
