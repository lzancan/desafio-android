package com.picpay.desafio.android.data.datasource

import com.picpay.desafio.android.data.response.UserResponse
import com.picpay.desafio.android.util.Result
import com.picpay.desafio.android.util.ResultError

interface RemoteDataSource {

    suspend fun getUsers(): Result<List<UserResponse>, ResultError>
}