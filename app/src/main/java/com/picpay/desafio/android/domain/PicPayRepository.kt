package com.picpay.desafio.android.domain

import com.picpay.desafio.android.data.response.UserResponse

interface PicPayRepository {

    suspend fun getUsers(): List<UserResponse>
}