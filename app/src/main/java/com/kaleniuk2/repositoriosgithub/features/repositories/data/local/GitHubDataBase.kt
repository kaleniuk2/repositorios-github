package com.kaleniuk2.repositoriosgithub.features.repositories.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kaleniuk2.repositoriosgithub.features.repositories.data.local.model.LocalGitHubItem

@Database(
    entities = [LocalGitHubItem::class],
    version = 1
)

abstract class GitHubDataBase : RoomDatabase() {

    abstract val gitHubDao: GitHubDao

    companion object {
        const val DATABASE_NAME = "github_db"
    }
}