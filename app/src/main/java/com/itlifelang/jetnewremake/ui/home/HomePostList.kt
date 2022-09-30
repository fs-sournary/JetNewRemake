package com.itlifelang.jetnewremake.ui.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.itlifelang.jetnewremake.data.posts
import com.itlifelang.jetnewremake.model.Post
import com.itlifelang.jetnewremake.model.PostsFeed
import com.itlifelang.jetnewremake.ui.component.AppDivider
import com.itlifelang.jetnewremake.ui.theme.AppTheme
import com.itlifelang.jetnewremake.util.CompletePreview

@Composable
fun HomePostList(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    state: LazyListState = rememberLazyListState(),
    showExpandedSearch: Boolean,
    searchInput: String,
    onSearchInputChange: (String) -> Unit,
    showClearSearch: Boolean,
    onClearSearchClick: () -> Unit,
    postsFeed: PostsFeed,
    onHighlightPostItemClick: (Post) -> Unit,
    onRecommendPostItemClick: (Post) -> Unit,
    onRecommendPostBookmarkChange: (Post, Boolean) -> Unit,
    onPopularPostItemClick: (Post) -> Unit,
    onRecentPostItemClick: (Post) -> Unit,
    onMoreActionRecentPostItemClick: (Post) -> Unit
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = contentPadding,
        state = state
    ) {
        if (showExpandedSearch) {
            item {
                HomeSearch(
                    searchInput = searchInput,
                    onSearchInputChange = onSearchInputChange,
                    modifier = Modifier.padding(16.dp),
                    showTrailingIcon = showClearSearch,
                    onTrailingIconClick = onClearSearchClick
                )
            }
        }
        item {
            HomeHighlightPostItem(
                post = postsFeed.highlightedPost,
                onPostClick = onHighlightPostItemClick
            )
        }
        if (postsFeed.recommendedPosts.isNotEmpty()) {
            val recommendPosts = postsFeed.recommendedPosts
            itemsIndexed(
                items = recommendPosts,
                key = { _, post -> post.id }
            ) { index, post ->
                HomeRecommendPostItem(
                    post = post,
                    onPostItemClick = onRecommendPostItemClick,
                    onBookmarkChange = onRecommendPostBookmarkChange
                )
                if (index != recommendPosts.lastIndex) {
                    AppDivider()
                }
            }
        }
        if (postsFeed.popularPosts.isNotEmpty()) {
            item {
                HomePopularPostSection(
                    posts = postsFeed.popularPosts,
                    onPostItemClick = onPopularPostItemClick
                )
            }
        }
        if (postsFeed.recentPosts.isNotEmpty()) {
            val recentPosts = postsFeed.recentPosts
            itemsIndexed(
                items = recentPosts,
                key = { _, post -> post.id }
            ) { index, post ->
                HomeRecentPostItem(
                    post = post,
                    onItemClick = onRecentPostItemClick,
                    onMoreActionClick = onMoreActionRecentPostItemClick
                )
                if (index != recentPosts.lastIndex) {
                    AppDivider()
                }
            }
        }
    }
}

@CompletePreview
@Composable
fun PreviewPostList() {
    AppTheme {
        Surface {
            HomePostList(
                showExpandedSearch = true,
                searchInput = "",
                onSearchInputChange = {},
                showClearSearch = true,
                onClearSearchClick = { /*TODO*/ },
                postsFeed = posts,
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
