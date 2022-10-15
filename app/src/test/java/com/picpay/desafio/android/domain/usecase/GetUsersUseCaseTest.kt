package com.picpay.desafio.android.domain.usecase

import com.picpay.desafio.android.data.response.UserResponse
import com.picpay.desafio.android.domain.PicPayRepository
import com.picpay.desafio.android.domain.mapper.UserMapper.mapToModel
import com.picpay.desafio.android.domain.mapper.UserTestFactory
import com.picpay.desafio.android.util.Result
import com.picpay.desafio.android.util.ResultError
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

@ExperimentalCoroutinesApi
class GetUsersUseCaseTest {

    private val repository: PicPayRepository = mockk()

    private val getUsers = GetUsers(
        repository
    )

    @Test
    fun `should call repository with list when getUsers Success`() = runTest {
        val returnResult = Result.Success(listOf(UserTestFactory.makeUserResponse()))
        val resultMapped = returnResult.value.mapToModel()
        prepareScenario(
            result = returnResult
        )

        val response = getUsers()

        coVerify(exactly = 1) { repository.getUsers() }
        Assert.assertEquals(resultMapped.single(), response.get()?.single())
    }

    @Test
    fun `should call repository with error when getUsers Error`() = runTest {
        val returnResult = Result.Error(ResultError.NetworkError)
        prepareScenario(
            result = returnResult
        )

        val response = getUsers()

        coVerify(exactly = 1) { repository.getUsers() }
        Assert.assertEquals(returnResult, response)
    }

    private fun prepareScenario(
        result: Result<List<UserResponse>, ResultError>
    ) {
        coEvery { repository.getUsers() } returns result
    }
}