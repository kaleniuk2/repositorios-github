package com.kaleniuk2.repositoriosgithub.features.repositories.presentation.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.compose.runtime.State
import androidx.lifecycle.Observer
import com.kaleniuk2.repositoriosgithub.commons.NetworkUtil
import com.kaleniuk2.repositoriosgithub.features.repositories.domain.repository.EmptyLocalDataAndNoInternetException
import com.kaleniuk2.repositoriosgithub.features.repositories.domain.usecases.GetGitHubRepositories
import com.kaleniuk2.repositoriosgithub.util.TestCoroutineRule
import io.mockk.*
import junit.framework.Assert.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GitHubRepositoriesViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()


    private var getRepositoriesUseCase: GetGitHubRepositories = mockk(relaxed = true)
    private var networkUtil: NetworkUtil = mockk(relaxed = true)
    private lateinit var viewModel: GitHubRepositoriesViewModel
    private var observerLoading: Observer<State<Boolean>> = mockk(relaxed = true)
    private var observerState: Observer<State<GitHubListState>> = mockk(relaxed = true)

    @Before
    fun init() {
        viewModel = GitHubRepositoriesViewModel(getRepositoriesUseCase, networkUtil)
    }

    @Test
    fun `given Call OnEvent With GetItems Event Should Call GetConnectionType`() {
        viewModel.onEvent(GitHubListEvent.GetItems)

        verify {
            networkUtil.getConnectionType()
        }
    }

    @Test
    fun `given Call OnEvent With GetItems Event And UseCase Return Success Should Return Success State`() {
        every { networkUtil.getConnectionType() } returns 1
        coEvery { getRepositoriesUseCase.invoke() } returns listOf(mockk(relaxed = true))
        viewModel.onEvent(GitHubListEvent.GetItems)

        coVerify {
            networkUtil.getConnectionType()
            getRepositoriesUseCase.invoke()
        }

        assertFalse(viewModel.loading.value)
        assertTrue(viewModel.state.value.items.isNotEmpty())
    }


    @Test
    fun `given Call OnEvent With GetItems Event And UseCase Return Error Should Return Error State`() {
        every { networkUtil.getConnectionType() } returns 1
        coEvery { getRepositoriesUseCase.invoke() } throws EmptyLocalDataAndNoInternetException()
        viewModel.onEvent(GitHubListEvent.GetItems)

        coVerify {
            networkUtil.getConnectionType()
            getRepositoriesUseCase.invoke()
            viewModel.emitError()
        }

        assertFalse(viewModel.loading.value)
    }
}