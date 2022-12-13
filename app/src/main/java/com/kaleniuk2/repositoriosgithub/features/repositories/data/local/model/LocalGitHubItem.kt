package com.kaleniuk2.repositoriosgithub.features.repositories.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kaleniuk2.repositoriosgithub.features.repositories.domain.model.GitHubItem

@Entity(
    tableName = ""
)
data class LocalGitHubItem(
    @PrimaryKey
    val id: Int,
    val name: String,
    val fullName: String,
    val private: Boolean,
    val owner: LocalOwner,
) {
    fun toItemRepository() = GitHubItem(
        id = id,
        name = name,
        fullName = fullName,
        private = private,
        owner = owner.toOwner()
    )
}
