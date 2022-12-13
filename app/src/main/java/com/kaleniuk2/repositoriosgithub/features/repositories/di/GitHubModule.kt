package com.kaleniuk2.repositoriosgithub.features.repositories.di

import com.kaleniuk2.repositoriosgithub.features.repositories.data.remote.GitHubService
import com.kaleniuk2.repositoriosgithub.features.repositories.domain.repository.GitHubRepository
import com.kaleniuk2.repositoriosgithub.features.repositories.domain.usecases.GetGitHubRepositories
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
object GitHubModule {

    @Provides
    fun providesGitHubService(okHttpClient: OkHttpClient, baseUrl: String): GitHubService {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .build()
            .create(GitHubService::class.java)
    }

    @Provides
    fun providesGetUseCase(repository: GitHubRepository): GetGitHubRepositories {
        return GetGitHubRepositories(repository)
    }
}