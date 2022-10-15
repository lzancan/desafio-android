package com.picpay.desafio.android.data.datasource

import com.picpay.desafio.android.data.api.PicPayService
import com.picpay.desafio.android.data.response.UserResponse
import com.picpay.desafio.android.util.Result
import com.picpay.desafio.android.util.ResultError
import javax.inject.Inject

class RemoteDefaultDataSource @Inject constructor(
    private val api: PicPayService
): RemoteDataSource {
    override suspend fun getUsers(): Result<List<UserResponse>, ResultError> {
        return try {
            Result.Success(api.getUsers())
        } catch (exception: Exception) {
            Result.Error(ResultError.Error)
        }
    }

}