package com.kaleniuk2.repositoriosgithub.features.repositories.data.local.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kaleniuk2.repositoriosgithub.features.repositories.data.local.LocalConstants.TABLE_NAME
import com.kaleniuk2.repositoriosgithub.features.repositories.domain.model.GitHubItem

@Entity(
    tableName = TABLE_NAME
)
data class LocalGitHubItem(
    @PrimaryKey
    var id: Int = 0,
    var name: String = "",
    var fullName: String = "",
    var private: Boolean = true,
    @Embedded
    var owner: LocalOwner = LocalOwner(),
) {
    fun toItemRepository() = GitHubItem(
        id = id,
        name = name,
        fullName = fullName,
        private = private,
        owner = owner.toOwner()
    )
}
