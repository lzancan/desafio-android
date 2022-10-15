package com.picpay.desafio.android.domain.usecase

import com.picpay.desafio.android.domain.PicPayRepository
import com.picpay.desafio.android.domain.mapper.UserMapper.mapToModel
import com.picpay.desafio.android.domain.model.User
import com.picpay.desafio.android.util.Result
import com.picpay.desafio.android.util.ResultError
import javax.inject.Inject

class GetUsers @Inject constructor(
    private val repository: PicPayRepository
): GetUsersUseCase {
    override suspend fun invoke(): Result<List<User>, ResultError> {
        return repository.getUsers().mapSuccess {
            it.mapToModel()
        }.mapError {
            it
        }
    }

}