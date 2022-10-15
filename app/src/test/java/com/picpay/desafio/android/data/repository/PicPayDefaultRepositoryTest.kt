package com.picpay.desafio.android.data.repository

import com.picpay.desafio.android.data.PicPayDefaultRepository
import com.picpay.desafio.android.data.datasource.RemoteDataSource
import com.picpay.desafio.android.data.response.UserResponse
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
class PicPayDefaultRepositoryTest {

    private val remoteDataSource: RemoteDataSource = mockk()

    private val repository = PicPayDefaultRepository(
        remoteDataSource
    )

    @Test
    fun `should call dataSource with list when getUsers Success`() = runTest{
        val returnResult = Result.Success(listOf<UserResponse>())
        prepareScenario(
            result = returnResult
        )

        val response = repository.getUsers()

        coVerify(exactly = 1) { remoteDataSource.getUsers() }
        Assert.assertEquals(returnResult, response)
    }

    @Test
    fun `should call dataSource with error when getUsers Error`() = runTest{
        val returnResult = Result.Error(ResultError.NetworkError)
        prepareScenario(
            result = returnResult
        )

        val response = repository.getUsers()

        coVerify(exactly = 1) { remoteDataSource.getUsers() }
        Assert.assertEquals(returnResult, response)
    }

    private fun prepareScenario(
        result: Result<List<UserResponse>, ResultError>
    ) {
        coEvery { remoteDataSource.getUsers() } returns result
    }
}