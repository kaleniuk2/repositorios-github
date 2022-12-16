package com.kaleniuk2.repositoriosgithub.features.repositories.presentation.list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kaleniuk2.repositoriosgithub.R
import com.kaleniuk2.repositoriosgithub.features.repositories.domain.repository.EmptyLocalDataAndNoInternetException
import com.kaleniuk2.repositoriosgithub.features.repositories.domain.usecases.GetGitHubRepositories
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GitHubRepositoriesViewModel @Inject constructor(val getGitHubRepositories: GetGitHubRepositories) :
    ViewModel() {

    private val _errorState = MutableSharedFlow<UiEvent>()
    val errorState = _errorState.asSharedFlow()

    private val _state = mutableStateOf(GitHubListState())
    val state: State<GitHubListState> = _state

    init {
        getItems()
    }

    fun onEvent(event: GitHubListEvent) {
        when (event) {
            is GitHubListEvent.GetItems -> {
                getItems()
            }
        }
    }

    private fun getItems() {
        viewModelScope.launch {
            _state.value = _state.value.copy(showLoading = true)
            try {
                val result = getGitHubRepositories()
                val currentItems = _state.value.items
                _state.value = _state.value.copy(items = currentItems + result)
            } catch (e: EmptyLocalDataAndNoInternetException) {
                _errorState.emit(UiEvent.ShowSnackBar(R.string.error))
            } catch (e: Exception) {
                _errorState.emit(UiEvent.ShowSnackBar(R.string.unknown_error))
            }
            _state.value = _state.value.copy(showLoading = false)
        }
    }
}