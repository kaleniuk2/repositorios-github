package com.kaleniuk2.repositoriosgithub.features.repositories.data.repository

import com.kaleniuk2.repositoriosgithub.commons.NetworkUtil
import com.kaleniuk2.repositoriosgithub.features.repositories.data.local.GitHubDao
import com.kaleniuk2.repositoriosgithub.features.repositories.data.remote.GitHubService
import com.kaleniuk2.repositoriosgithub.features.repositories.data.remote.util.apiCall
import com.kaleniuk2.repositoriosgithub.features.repositories.domain.model.GitHubItem
import com.kaleniuk2.repositoriosgithub.features.repositories.domain.repository.EmptyLocalDataAndNoInternetException
import com.kaleniuk2.repositoriosgithub.features.repositories.domain.repository.GitHubRepository
import javax.inject.Inject

class GitHubRepositoryImpl @Inject constructor(
    val dao: GitHubDao,
    val service: GitHubService,
    val networkUtil: NetworkUtil
): GitHubRepository {
    private var currentPage = 1

    override suspend fun getAll(): List<GitHubItem> {
         if (networkUtil.getConnectionType() != 0) {
             val response = apiCall { service.getAll() }

             if (response.isSuccess && response.getOrNull() != null) {
                 checkCurrentPageToDeleteItems()

                 val listItems = response.getOrNull()?.items ?: emptyList()
                 currentPage++
                 return listItems.map {
                     dao.insertRepository(it.toDbRepository())
                     it.toItemRepository()
                 }
             }

             if (currentPage == 1) {
                 throw EmptyLocalDataAndNoInternetException()
             }
             return getFromDb()
         }
        return getFromDb()
    }

    private suspend fun checkCurrentPageToDeleteItems() {
        if (currentPage == 1)
            dao.removeAllItems()
    }

    private suspend fun getFromDb(): List<GitHubItem> {
        return dao.getRepositories().map { it.toItemRepository() }
    }

    companion object {
        private const val ITEMS_PER_PAGE = 30
    }
}