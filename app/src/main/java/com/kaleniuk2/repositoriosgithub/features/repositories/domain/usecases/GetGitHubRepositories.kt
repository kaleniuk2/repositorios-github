package com.kaleniuk2.repositoriosgithub.features.repositories.domain.usecases

import com.kaleniuk2.repositoriosgithub.features.repositories.domain.model.GitHubItem
import com.kaleniuk2.repositoriosgithub.features.repositories.domain.repository.GitHubRepository
import javax.inject.Inject

class GetGitHubRepositories constructor(
    private val repository: GitHubRepository
) {
    suspend operator fun invoke(): List<GitHubItem> {
        return repository.getAll()
    }
}