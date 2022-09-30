package com.itlifelang.jetnewremake.ui.interest.topic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.itlifelang.jetnewremake.repository.interest.InterestRepository
import com.itlifelang.jetnewremake.ui.util.toUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class InterestTopicViewModel @Inject constructor(
    private val interestRepository: InterestRepository
) : ViewModel(){
    private val _uiState = MutableStateFlow(InterestTopicUiState())
    val uiState: StateFlow<InterestTopicUiState> = _uiState.asStateFlow()

    init {
        getTopicSections()
    }

    fun getTopicSections() {
        viewModelScope.launch {
            interestRepository.getTopicSections().collect { topicSectionResult ->
                val topicUiState = topicSectionResult.toUiState { topicSections ->
                    val topicUis = mutableListOf<TopicUi>()
                    topicSections.map { topicSection ->
                        val title = topicSection.title
                        topicUis.add(TopicUi.Separator(title))
                        val topicUIItems = topicSection.interests.map { TopicUi.Item(title, it) }
                        topicUis.addAll(topicUIItems)
                    }
                    topicUis
                }
                _uiState.update { it.copy(topicState = topicUiState) }
            }
        }
    }
}