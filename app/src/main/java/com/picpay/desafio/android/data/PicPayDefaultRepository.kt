package com.picpay.desafio.android.data

import com.picpay.desafio.android.data.datasource.RemoteDataSource
import com.picpay.desafio.android.data.response.UserResponse
import com.picpay.desafio.android.domain.PicPayRepository
import com.picpay.desafio.android.util.ResultError
import com.picpay.desafio.android.util.Result
import javax.inject.Inject

class PicPayDefaultRepository @Inject constructor(
    private val dataSource: RemoteDataSource
): PicPayRepository {
    override suspend fun getUsers(): Result<List<UserResponse>, ResultError> {
        return dataSource.getUsers()
    }
}