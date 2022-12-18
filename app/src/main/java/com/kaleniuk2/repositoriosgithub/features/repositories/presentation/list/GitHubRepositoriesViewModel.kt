package com.kaleniuk2.repositoriosgithub.features.repositories.presentation.list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kaleniuk2.repositoriosgithub.R
import com.kaleniuk2.repositoriosgithub.commons.NetworkUtil
import com.kaleniuk2.repositoriosgithub.features.repositories.domain.repository.EmptyLocalDataAndNoInternetException
import com.kaleniuk2.repositoriosgithub.features.repositories.domain.repository.HasConsultedDbAndNoInternetException
import com.kaleniuk2.repositoriosgithub.features.repositories.domain.repository.UnknownErrorException
import com.kaleniuk2.repositoriosgithub.features.repositories.domain.usecases.GetGitHubRepositories
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GitHubRepositoriesViewModel @Inject constructor(
    private val getGitHubRepositories: GetGitHubRepositories,
    private val networkUtil: NetworkUtil
) :
    ViewModel() {

    private val _errorState = MutableSharedFlow<UiEvent>()
    val errorState = _errorState.asSharedFlow()

    private val _state = mutableStateOf(GitHubListState())
    val state: State<GitHubListState> = _state

    private val _loading = mutableStateOf(false)
    val loading: State<Boolean> = _loading

    private var hasConsultedDb = false

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
            if (networkUtil.getConnectionType() == 0 && hasConsultedDb)
                return@launch

            _loading.value = true
            try {
                val result = getGitHubRepositories()
                val currentItems = _state.value.items.toMutableList()
                currentItems.addAll(result)
                _state.value = _state.value.copy(items = currentItems)
            } catch (e: EmptyLocalDataAndNoInternetException) {
                emitError()
            } catch (e: UnknownErrorException) {
                _errorState.emit(UiEvent.ShowSnackBar(R.string.unknown_error))
            } catch (e: HasConsultedDbAndNoInternetException) {
                _errorState.emit(UiEvent.ShowSnackBar(R.string.no_internet))
                hasConsultedDb = true
            } catch (e: Exception) {
                _errorState.emit(UiEvent.ShowSnackBar(R.string.unknown_error))
            }
            _loading.value = false

        }
    }

    fun emitError() {
        viewModelScope.launch {
            _errorState.emit(UiEvent.ShowSnackBar(R.string.error))
        }
    }
}