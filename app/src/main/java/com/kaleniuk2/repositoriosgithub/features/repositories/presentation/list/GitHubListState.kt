package com.kaleniuk2.repositoriosgithub.features.repositories.presentation.list

import com.kaleniuk2.repositoriosgithub.features.repositories.domain.model.GitHubItem

data class GitHubListState(
    var items: List<GitHubItem> = mutableListOf(),
    var showLoading: Boolean = false
)