package com.kaleniuk2.repositoriosgithub.features.repositories.data.local

import androidx.room.*
import com.kaleniuk2.repositoriosgithub.features.repositories.data.local.LocalConstants.TABLE_NAME
import com.kaleniuk2.repositoriosgithub.features.repositories.data.local.model.LocalGitHubItem
import kotlinx.coroutines.flow.Flow

@Dao
interface GitHubDao {

    @Query("SELECT * FROM $TABLE_NAME")
    suspend fun getRepositories(): List<LocalGitHubItem>

    @Query("SELECT * FROM $TABLE_NAME WHERE id = :id")
    suspend fun getRepositoryById(id: Int): LocalGitHubItem?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRepository(repository: LocalGitHubItem)

    @Query("DELETE FROM $TABLE_NAME")
    suspend fun removeAllItems()
}