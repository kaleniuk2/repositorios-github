package com.kaleniuk2.repositoriosgithub.features.repositories.domain.model

data class ItemRepository(
    val id: Int,
    val name: String,
    val fullName: String,
    val private: Boolean,
    val owner: Owner,
)