package com.kaleniuk2.repositoriosgithub.features.repositories.presentation.list

import androidx.lifecycle.ViewModel
import com.kaleniuk2.repositoriosgithub.features.repositories.domain.usecases.GetGitHubRepositories
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GitHubRepositoriesViewModel @Inject constructor(val getGitHubRepositories: GetGitHubRepositories) :
    ViewModel() {


}