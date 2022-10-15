package com.picpay.desafio.android.data.datasource

import com.picpay.desafio.android.CoroutineTestRule
import com.picpay.desafio.android.UserTestFactory
import com.picpay.desafio.android.data.dao.UserDao
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class LocalDefaultDataSourceTest {

    @get:Rule
    val coroutinesTestRule = CoroutineTestRule()

    private val userDao: UserDao = mockk()

    private val dataSource = LocalDefaultDataSource(
        userDao
    )

    @Test
    fun `should call userDao when call getUsers`() = runTest{
        prepareScenario()

        dataSource.getUsers()

        coVerify(exactly = 1) { userDao.getAllUsers() }
    }

    @Test
    fun `should call userDao when call insertUser`() = runTest{
        prepareScenario()

        dataSource.insertUsers(arrayOf(UserTestFactory.makeUserDataModel()))

        coVerify(exactly = 1) { userDao.insertUsers(any()) }
    }

    private fun prepareScenario() {
        coEvery { userDao.getAllUsers() } returns listOf()
        coEvery { userDao.insertUsers(any()) } just runs
    }
}