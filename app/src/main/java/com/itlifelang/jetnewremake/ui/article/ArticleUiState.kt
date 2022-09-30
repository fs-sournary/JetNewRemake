package com.itlifelang.jetnewremake.ui.article

import com.itlifelang.jetnewremake.model.Post

data class ArticleUiState(
    val publicationName: String = "",
    val postState: ArticlePostState = ArticlePostState.Loading
)

sealed class ArticlePostState {
    object Loading : ArticlePostState()

    data class Success(val post: Post) : ArticlePostState()

    data class Error(val exception: Exception) : ArticlePostState()
}