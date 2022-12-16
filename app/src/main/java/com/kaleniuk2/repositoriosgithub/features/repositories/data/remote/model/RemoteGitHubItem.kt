package com.kaleniuk2.repositoriosgithub.features.repositories.data.remote.model

import com.google.gson.annotations.SerializedName
import com.kaleniuk2.repositoriosgithub.features.repositories.data.local.model.LocalGitHubItem
import com.kaleniuk2.repositoriosgithub.features.repositories.data.remote.model.RemoteOwner
import com.kaleniuk2.repositoriosgithub.features.repositories.domain.model.GitHubItem

data class RemoteGitHubItem(
    val id: Int,
    val name: String,
    @SerializedName("full_name")
    val fullName: String,
    @SerializedName("html_url")
    val htmlUrl: String,
    val private: Boolean,
    val forks: Int,
    val description: String,
    val owner: RemoteOwner,
) {
    fun toItemRepository() = GitHubItem(
        id = id,
        name = name,
        fullName = fullName,
        private = private,
        forks = forks,
        htmlUrl = htmlUrl,
        description = description,
        owner = owner.toOwner()
    )

    fun toDbRepository() = LocalGitHubItem(
        id = id,
        name = name,
        fullName = fullName,
        private = private,
        description = description,
        forks = forks,
        owner = owner.toDBOwner()
    )
}