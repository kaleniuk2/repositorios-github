package com.kaleniuk2.repositoriosgithub.di

import android.app.Application
import androidx.room.Room
import com.kaleniuk2.repositoriosgithub.commons.NetworkUtil
import com.kaleniuk2.repositoriosgithub.features.repositories.data.local.GitHubDataBase
import com.kaleniuk2.repositoriosgithub.features.repositories.data.remote.GitHubService
import com.kaleniuk2.repositoriosgithub.features.repositories.data.repository.GitHubRepositoryImpl
import com.kaleniuk2.repositoriosgithub.features.repositories.domain.repository.GitHubRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(app: Application): GitHubDataBase {
        return Room.databaseBuilder(
            app,
            GitHubDataBase::class.java,
            GitHubDataBase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideNetworkUtil(app: Application): NetworkUtil {
        return NetworkUtil(app)
    }

    @Provides
    @Singleton
    fun provideBaseUrl(): String {
        return "https://api.github.com/"
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }
}