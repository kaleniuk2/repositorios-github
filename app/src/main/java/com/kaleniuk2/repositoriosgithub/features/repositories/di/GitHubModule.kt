package com.kaleniuk2.repositoriosgithub.features.repositories.di

import com.kaleniuk2.repositoriosgithub.commons.NetworkUtil
import com.kaleniuk2.repositoriosgithub.features.repositories.data.local.GitHubDataBase
import com.kaleniuk2.repositoriosgithub.features.repositories.data.remote.GitHubService
import com.kaleniuk2.repositoriosgithub.features.repositories.data.repository.GitHubRepositoryImpl
import com.kaleniuk2.repositoriosgithub.features.repositories.domain.repository.GitHubRepository
import com.kaleniuk2.repositoriosgithub.features.repositories.domain.usecases.GetGitHubRepositories
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
object GitHubModule {

    @Provides
    @ViewModelScoped
    fun providesGitHubService(okHttpClient: OkHttpClient, baseUrl: String): GitHubService {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .build()
            .create(GitHubService::class.java)
    }

    @Provides
    @ViewModelScoped
    fun providesGetUseCase(repository: GitHubRepository): GetGitHubRepositories {
        return GetGitHubRepositories(repository)
    }

    @Provides
    @ViewModelScoped
    fun provideRepository(
        db: GitHubDataBase,
        service: GitHubService,
        networkUtil: NetworkUtil
    ): GitHubRepository {
        return GitHubRepositoryImpl(
            db.gitHubDao,
            service,
            networkUtil
        )
    }
}