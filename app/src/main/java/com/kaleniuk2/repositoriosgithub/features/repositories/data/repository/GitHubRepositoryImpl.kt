package com.kaleniuk2.repositoriosgithub.features.repositories.data.repository

import com.kaleniuk2.repositoriosgithub.commons.NetworkUtil
import com.kaleniuk2.repositoriosgithub.features.repositories.data.local.GitHubDao
import com.kaleniuk2.repositoriosgithub.features.repositories.data.remote.GitHubService
import com.kaleniuk2.repositoriosgithub.features.repositories.data.remote.util.apiCall
import com.kaleniuk2.repositoriosgithub.features.repositories.domain.model.GitHubItem
import com.kaleniuk2.repositoriosgithub.features.repositories.domain.repository.EmptyLocalDataAndNoInternetException
import com.kaleniuk2.repositoriosgithub.features.repositories.domain.repository.GitHubRepository
import com.kaleniuk2.repositoriosgithub.features.repositories.domain.repository.HasConsultedDbAndNoInternetException
import com.kaleniuk2.repositoriosgithub.features.repositories.domain.repository.UnknownErrorException
import javax.inject.Inject

class GitHubRepositoryImpl @Inject constructor(
    val dao: GitHubDao,
    val service: GitHubService,
    val networkUtil: NetworkUtil
) : GitHubRepository {
    private var currentPage = 1
    private var hasConsuledtDb = false

    override suspend fun getAll(): List<GitHubItem> {
        if (networkUtil.getConnectionType() != 0) {
            val response = apiCall { service.getAll(page = currentPage) }

            if (response.isSuccess && response.getOrNull() != null) {
                checkCurrentPageToDeleteItems()

                val listItems = response.getOrNull()?.items ?: emptyList()
                currentPage++
                return listItems.map {
                    dao.insertRepository(it.toDbRepository())
                    it.toItemRepository()
                }
            }
            throw UnknownErrorException()
        }
        val resultFromDB = getFromDb()

        if (resultFromDB.isEmpty() && currentPage == 1) {
            throw EmptyLocalDataAndNoInternetException()
        }

        if (hasConsuledtDb)
            throw HasConsultedDbAndNoInternetException()
        hasConsuledtDb = true
        return resultFromDB
    }

    private suspend fun checkCurrentPageToDeleteItems() {
        if (currentPage == 1)
            dao.removeAllItems()
    }

    private suspend fun getFromDb(): List<GitHubItem> {
        return dao.getRepositories().map { it.toItemRepository() }
    }
}