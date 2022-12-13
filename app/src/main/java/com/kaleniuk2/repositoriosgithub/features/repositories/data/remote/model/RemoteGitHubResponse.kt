package com.kaleniuk2.repositoriosgithub.features.repositories.data.remote.model

import com.google.gson.annotations.SerializedName

data class RemoteGitHubResponse(
    @SerializedName("total_count")
    val totalCount: Int,

    @SerializedName("items")
    val items: List<RemoteGitHubItem>
)