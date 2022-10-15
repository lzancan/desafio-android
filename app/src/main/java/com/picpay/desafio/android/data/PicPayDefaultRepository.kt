package com.picpay.desafio.android.data

import com.picpay.desafio.android.data.datasource.LocalDataSource
import com.picpay.desafio.android.data.datasource.RemoteDataSource
import com.picpay.desafio.android.domain.PicPayRepository
import com.picpay.desafio.android.domain.mapper.UserMapper.mapDataModelToModel
import com.picpay.desafio.android.domain.mapper.UserMapper.mapResponseToDataModel
import com.picpay.desafio.android.domain.model.User
import com.picpay.desafio.android.util.ResultError
import com.picpay.desafio.android.util.Result
import javax.inject.Inject

class PicPayDefaultRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
): PicPayRepository {
    override suspend fun getUsers(): Result<List<User>, ResultError> {
        remoteDataSource.getUsers()
            .onSuccess {
                localDataSource.insertUsers(it.mapResponseToDataModel().toTypedArray())
            }
        return getFromDatabase()
    }

    private suspend fun getFromDatabase(): Result<List<User>, ResultError> {
        return localDataSource.getUsers()
            .mapSuccess {
                it.mapDataModelToModel()
            }
            .mapError {
                ResultError.Error
            }
    }
}