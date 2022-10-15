package com.picpay.desafio.android.domain

import com.picpay.desafio.android.domain.model.User
import com.picpay.desafio.android.util.Result
import com.picpay.desafio.android.util.ResultError

interface PicPayRepository {

    suspend fun getUsers(): Result<List<User>, ResultError>
}