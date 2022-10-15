package com.picpay.desafio.android.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.picpay.desafio.android.CoroutineTestRule
import com.picpay.desafio.android.data.response.UserResponse
import com.picpay.desafio.android.domain.mapper.UserTestFactory
import com.picpay.desafio.android.domain.model.User
import com.picpay.desafio.android.domain.usecase.GetUsersUseCase
import com.picpay.desafio.android.util.Result
import com.picpay.desafio.android.util.ResultError
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MainViewModelTest {

    @get:Rule
    val instantTask = InstantTaskExecutorRule()

    @get:Rule
    val corutineTestRule = CoroutineTestRule()

    private val getUsersUseCase: GetUsersUseCase = mockk(relaxed = true)

    private val observerListSuccess: Observer<List<User>> = mockk(relaxed = true)
    private val observerError: Observer<ResultError> = mockk(relaxed = true)

    private lateinit var viewModel: MainViewModel

    @Test
    fun `should call getUsersUseCase on viewModel fetchData`() = runTest {
        prepareScenario()

        viewModel.fetchData()

        coVerify(exactly = 1) { getUsersUseCase() }
    }

    @Test
    fun `should set usersLoaded on viewModel fetchData Success`() = runTest {
        val returnResult = Result.Success(listOf(UserTestFactory.makeUser()))
        prepareScenario(
            result = returnResult
        )

        viewModel.fetchData()

        verify { observerListSuccess.onChanged(returnResult.value) }
    }

    @Test
    fun `should set loadError on viewModel fetchData Error`() = runTest {
        val returnResult = Result.Error(ResultError.NetworkError)
        prepareScenario(
            result = returnResult
        )

        viewModel.fetchData()

        verify { observerError.onChanged(returnResult.value) }
    }

    private fun prepareScenario(
        result: Result<List<User>, ResultError> = Result.Success(listOf())
    ) {
        coEvery { getUsersUseCase() } returns result
        viewModel = MainViewModel(
            getUsersUseCase
        )
        viewModel.usersLoaded.observeForever(observerListSuccess)
        viewModel.loadError.observeForever(observerError)
    }
}
