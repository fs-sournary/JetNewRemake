package com.itlifelang.jetnewremake.ui.home

import com.itlifelang.jetnewremake.model.PostsFeed
import com.itlifelang.jetnewremake.ui.util.UiState

data class HomeUiState(
    val postsFeedState: UiState<PostsFeed> = UiState.Loading,
    val searchInput: String = ""
)
