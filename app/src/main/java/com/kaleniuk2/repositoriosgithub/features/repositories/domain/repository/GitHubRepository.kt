package com.kaleniuk2.repositoriosgithub.features.repositories.domain.repository

import com.kaleniuk2.repositoriosgithub.features.repositories.domain.model.GitHubItem

interface GitHubRepository {
    suspend fun getAll(): List<GitHubItem>
}