package com.itlifelang.jetnewremake.ui.article

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.itlifelang.jetnewremake.repository.Result
import com.itlifelang.jetnewremake.repository.posts.PostsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class ArticleViewModel @Inject constructor(
    private val postRepository: PostsRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val id = savedStateHandle.get<String>("postId").orEmpty()
    private val publicationName = savedStateHandle.get<String>("publicationName").orEmpty()

    private val _uiState = MutableStateFlow(ArticleUiState(publicationName = publicationName))
    val uiState: StateFlow<ArticleUiState> = _uiState.asStateFlow()

    init {
        println("Article id: $id")
        getPost()
    }

    fun getPost() {
        viewModelScope.launch {
            postRepository.getPost(id).collect { postResult ->
                val postState = when (postResult) {
                    is Result.Loading -> ArticlePostState.Loading
                    is Result.Success -> ArticlePostState.Success(postResult.data)
                    is Result.Error -> ArticlePostState.Error(postResult.exception)
                }
                _uiState.update { it.copy(postState = postState) }
            }
        }
    }
}
