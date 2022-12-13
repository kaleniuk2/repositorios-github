package com.kaleniuk2.repositoriosgithub.features.repositories.data.remote

import com.kaleniuk2.repositoriosgithub.features.repositories.data.remote.model.RemoteGitHubResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GitHubService {

    @GET("search/repositories")
    suspend fun getAll(
        @Query("language") language: String = "kotlin",
        @Query("page") page: Int = 1,
        @Query("sort") sort: String = "stars"
    ): RemoteGitHubResponse
}