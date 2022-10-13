package com.picpay.desafio.android.data.datasource

import com.picpay.desafio.android.data.response.UserResponse

interface RemoteDataSource {

    suspend fun getUsers(): List<UserResponse>
}