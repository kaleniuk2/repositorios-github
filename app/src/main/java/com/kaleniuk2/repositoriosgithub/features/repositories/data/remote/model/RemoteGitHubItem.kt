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
    val private: Boolean,
    val owner: RemoteOwner,
) {
    fun toItemRepository() = GitHubItem(
        id = id,
        name = name,
        fullName = fullName,
        private = private,
        owner = owner.toOwner()
    )

    fun toDbRepository() = LocalGitHubItem(
        id = id,
        name = name,
        fullName = fullName,
        private = private,
        owner = owner.toDBOwner()
    )
}