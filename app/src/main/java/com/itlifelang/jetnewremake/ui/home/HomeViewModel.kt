package com.itlifelang.jetnewremake.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.itlifelang.jetnewremake.repository.Result
import com.itlifelang.jetnewremake.repository.posts.PostsRepository
import com.itlifelang.jetnewremake.ui.util.toUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val postsRepository: PostsRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        getPostsFeed()
    }

    fun getPostsFeed() {
        viewModelScope.launch {
            postsRepository.getPostsFeed().collect { postsFeedResult ->
                val postsFeedState = postsFeedResult.toUiState()
                _uiState.update { it.copy(postsFeedState = postsFeedState) }
            }
        }
    }

    fun changeSearchInput(searchInput: String) {
        _uiState.update { it.copy(searchInput = searchInput) }
    }
}
