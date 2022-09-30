package com.itlifelang.jetnewremake.ui.interest.people

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.itlifelang.jetnewremake.repository.interest.InterestRepository
import com.itlifelang.jetnewremake.ui.util.UiState
import com.itlifelang.jetnewremake.ui.util.toUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class InterestPeopleViewModel @Inject constructor(
    private val interestRepository: InterestRepository
) : ViewModel(){
    private val _uiState = MutableStateFlow<UiState<List<String>>>(UiState.Loading)
    val uiState: StateFlow<UiState<List<String>>> = _uiState

    init {
        getPeoples()
    }

    fun getPeoples() {
        viewModelScope.launch {
            interestRepository.getPeople().collect { peopleResult ->
                _uiState.update { peopleResult.toUiState() }
            }
        }
    }
}


