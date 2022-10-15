package com.picpay.desafio.android.data.datasource

import com.picpay.desafio.android.CoroutineTestRule
import com.picpay.desafio.android.data.api.PicPayService
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class RemoteDefaultDataSourceTest {

    @get:Rule
    val coroutinesTestRule = CoroutineTestRule()

    private val api: PicPayService = mockk()

    private val dataSource = RemoteDefaultDataSource(
        api
    )

    @Test
    fun `should call api when call getUsers`() = runTest{
        prepareScenario()

        dataSource.getUsers()

        coVerify(exactly = 1) { api.getUsers() }
    }

    private fun prepareScenario() {
        coEvery { api.getUsers() } returns listOf()
    }
}