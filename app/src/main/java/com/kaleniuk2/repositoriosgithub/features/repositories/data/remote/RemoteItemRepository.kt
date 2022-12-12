package com.kaleniuk2.repositoriosgithub.features.repositories.data.remote

import com.google.gson.annotations.SerializedName
import com.kaleniuk2.repositoriosgithub.features.repositories.domain.model.ItemRepository
import com.kaleniuk2.repositoriosgithub.features.repositories.domain.model.Owner

data class RemoteItemRepository(
    val id: Int,
    val name: String,
    @SerializedName("full_name")
    val fullName: String,
    val private: Boolean,
    val owner: RemoteOwner,
) {
    fun toItemRepository() = ItemRepository(
        id = id,
        name = name,
        fullName = fullName,
        private = private,
        owner = owner.toOwner()
    )
}