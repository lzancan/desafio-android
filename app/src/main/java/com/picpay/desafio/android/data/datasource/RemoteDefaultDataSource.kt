package com.picpay.desafio.android.data.datasource

import com.picpay.desafio.android.data.response.UserResponse

class RemoteDefaultDataSource: RemoteDataSource {
    override suspend fun getUsers(): List<UserResponse> {
        TODO("Not yet implemented")
    }

}