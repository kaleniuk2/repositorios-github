package com.kaleniuk2.repositoriosgithub.features.repositories.presentation.list

sealed class GitHubListEvent {
    object GetItems: GitHubListEvent()
}