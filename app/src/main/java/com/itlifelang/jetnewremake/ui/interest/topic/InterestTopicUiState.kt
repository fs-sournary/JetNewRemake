package com.itlifelang.jetnewremake.ui.interest.topic

import com.itlifelang.jetnewremake.ui.util.UiState

data class InterestTopicUiState(val topicState: UiState<List<TopicUi>> = UiState.Loading)

sealed class TopicUi {
    data class Item(val title: String, val interest: String) : TopicUi()

    data class Separator(val title: String) : TopicUi()
}
