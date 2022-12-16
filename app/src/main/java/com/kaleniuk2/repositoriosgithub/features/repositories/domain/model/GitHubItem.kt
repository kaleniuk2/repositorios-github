package com.kaleniuk2.repositoriosgithub.features.repositories.domain.model

data class GitHubItem(
    val id: Int,
    val name: String,
    val fullName: String,
    val private: Boolean,
    val forks: Int,
    val htmlUrl: String,
    val description: String,
    val owner: Owner,
)