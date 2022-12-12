package com.kaleniuk2.repositoriosgithub.features.repositories.data.local

import androidx.room.PrimaryKey
import com.kaleniuk2.repositoriosgithub.features.repositories.domain.model.ItemRepository
import com.kaleniuk2.repositoriosgithub.features.repositories.domain.model.Owner

data class LocalItemRepository(
    @PrimaryKey
    val id: Int,
    val name: String,
    val fullName: String,
    val private: Boolean,
    val owner: LocalOwner,
) {
    fun toItemRepository() = ItemRepository(
        id = id,
        name = name,
        fullName = fullName,
        private = private,
        owner = owner.toOwner()
    )
}
