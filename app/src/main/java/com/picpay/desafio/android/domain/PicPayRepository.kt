package com.picpay.desafio.android.domain

import com.picpay.desafio.android.data.response.UserResponse
import com.picpay.desafio.android.util.ResultError
import com.picpay.desafio.android.util.Result

interface PicPayRepository {

    suspend fun getUsers(): Result<List<UserResponse>, ResultError>
}