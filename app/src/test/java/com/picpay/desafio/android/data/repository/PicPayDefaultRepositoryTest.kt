package com.picpay.desafio.android.data.repository

import com.picpay.desafio.android.data.PicPayDefaultRepository
import com.picpay.desafio.android.data.datamodel.UserDataModel
import com.picpay.desafio.android.data.datasource.LocalDataSource
import com.picpay.desafio.android.data.datasource.RemoteDataSource
import com.picpay.desafio.android.data.response.UserResponse
import com.picpay.desafio.android.util.Result
import com.picpay.desafio.android.util.ResultError
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.coVerifyOrder
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

@ExperimentalCoroutinesApi
class PicPayDefaultRepositoryTest {

    private val remoteDataSource: RemoteDataSource = mockk()
    private val localDataSource: LocalDataSource = mockk()

    private val repository = PicPayDefaultRepository(
        remoteDataSource,
        localDataSource
    )

    @Test
    fun `should call remote datasource get and local dataSource insert and return local get when Success`() = runTest{
        val returnResult = Result.Success(listOf<UserResponse>())
        prepareScenario(
            resultRemote = returnResult
        )

        val response = repository.getUsers()

        coVerifyOrder {
            remoteDataSource.getUsers()
            localDataSource.insertUsers(any())
            localDataSource.getUsers()
        }
        Assert.assertEquals(returnResult, response)
    }

    @Test
    fun `should get local data when remote Error`() = runTest{
        val returnResultRemote = Result.Error(ResultError.Error)
        val returnResultLocal = Result.Success(listOf<UserDataModel>())
        prepareScenario(
            resultRemote = returnResultRemote,
            resultLocal = returnResultLocal
        )

        val response = repository.getUsers()

        coVerifyOrder {
            remoteDataSource.getUsers()
            localDataSource.getUsers()
        }
        Assert.assertEquals(returnResultLocal, response)
    }

    @Test
    fun `should return Error when local and remote Error`() = runTest{
        val returnResult = Result.Error(ResultError.Error)
        prepareScenario(
            resultRemote = returnResult,
            resultLocal = returnResult
        )

        val response = repository.getUsers()

        coVerifyOrder {
            remoteDataSource.getUsers()
            localDataSource.getUsers()
        }
        coVerify(exactly = 1) { remoteDataSource.getUsers() }
        Assert.assertEquals(returnResult, response)
    }

    private fun prepareScenario(
        resultRemote: Result<List<UserResponse>, ResultError> = Result.Success(listOf()),
        resultLocal: Result<List<UserDataModel>, ResultError> = Result.Success(listOf())
    ) {
        coEvery { remoteDataSource.getUsers() } returns resultRemote
        coEvery { localDataSource.getUsers() } returns resultLocal
        coEvery { localDataSource.insertUsers(any()) } just runs
    }
}