package com.picpay.desafio.android.data

import com.picpay.desafio.android.data.response.UserResponse
import com.picpay.desafio.android.domain.PicPayRepository

class PicPayDefaultRepository: PicPayRepository {
    override suspend fun getUsers(): List<UserResponse> {
        TODO("Not yet implemented")
    }
}