package com.picpay.desafio.android.domain.usecase

import com.picpay.desafio.android.domain.model.User
import com.picpay.desafio.android.util.Result
import com.picpay.desafio.android.util.ResultError

interface GetUsersUseCase {

    suspend operator fun invoke(): Result<List<User>, ResultError>
}