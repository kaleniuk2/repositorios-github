package com.kaleniuk2.repositoriosgithub.features.repositories.presentation.list

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.NavController
import com.kaleniuk2.repositoriosgithub.commons.NetworkUtil
import com.kaleniuk2.repositoriosgithub.features.repositories.domain.model.GitHubItem
import com.kaleniuk2.repositoriosgithub.features.repositories.domain.repository.EmptyLocalDataAndNoInternetException
import com.kaleniuk2.repositoriosgithub.features.repositories.domain.usecases.GetGitHubRepositories
import com.kaleniuk2.repositoriosgithub.ui.theme.RepositoriosGithubTheme
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GitHubRepositoriesScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var viewModel: GitHubRepositoriesViewModel
    private lateinit var getRepositoriesUseCase: GetGitHubRepositories
    private lateinit var networkUtil: NetworkUtil
    private lateinit var navController: NavController

    @Before
    fun init() {
        navController = mockk(relaxed = true)
        getRepositoriesUseCase = mockk()
        networkUtil = mockk()
    }

    @Test
    fun givenScreenFinishLoading_AndRequestSuccess_ShouldShowAllRepositoriesAndTitle() {
        mockNetwork(true)
        mockList(2)
        viewModel = GitHubRepositoriesViewModel(getRepositoriesUseCase, networkUtil)
        initScreen()
        composeTestRule.onNodeWithText("Repositórios").assertIsDisplayed()
        composeTestRule.onNodeWithText("fullname 1").assertIsDisplayed()

    }

    @Test
    fun givenScreenFinishLoading_AndRequestFailure_ShouldShowErroSnackbar() {
        mockNetwork(false)
        coEvery { getRepositoriesUseCase.invoke() } throws EmptyLocalDataAndNoInternetException()
        viewModel = GitHubRepositoriesViewModel(getRepositoriesUseCase, networkUtil)

        initScreen()

        viewModel.emitError()

        composeTestRule.onNodeWithText("Erro ao obter dados").assertIsDisplayed()
    }

    private fun initScreen() {
        composeTestRule.setContent {
            RepositoriosGithubTheme {
                GitHubRepositoriesScreen(navController, viewModel)
            }
        }
    }

    private fun mockNetwork(enableNetwork: Boolean) {
        every { networkUtil.getConnectionType() } returns if (enableNetwork) 1 else 0
    }

    private fun mockList(items: Int) {

        val list: List<GitHubItem> = (0..items).map {
            GitHubItem(
                it,
                "name $it",
                "fullname $it",
                true,
                it,
                "http://teste.com.br",
                "description $it",
                mockk(relaxed = true)
            )
        }
        coEvery { getRepositoriesUseCase.invoke() } returns list
    }
}