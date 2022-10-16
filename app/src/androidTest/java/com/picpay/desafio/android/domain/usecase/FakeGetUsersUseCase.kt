package com.picpay.desafio.android.domain.usecase

import com.picpay.desafio.android.domain.model.User
import com.picpay.desafio.android.domain.usecase.GetUsersUseCase
import com.picpay.desafio.android.util.Result
import com.picpay.desafio.android.util.ResultError
import javax.inject.Inject

class FakeGetUsersUseCase @Inject constructor(): GetUsersUseCase {
    lateinit var result: Result<List<User>, ResultError>

    override suspend fun invoke(): Result<List<User>, ResultError> {
        return result
    }
}