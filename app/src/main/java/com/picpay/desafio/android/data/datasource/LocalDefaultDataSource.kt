package com.picpay.desafio.android.data.datasource

import com.picpay.desafio.android.data.dao.UserDao
import com.picpay.desafio.android.data.datamodel.UserDataModel
import com.picpay.desafio.android.util.Result
import com.picpay.desafio.android.util.ResultError
import javax.inject.Inject

class LocalDefaultDataSource @Inject constructor(
    private val userDao: UserDao
): LocalDataSource {

    override suspend fun insertUsers(users: Array<UserDataModel>) {
        userDao.insertUsers(*users)
    }

    override suspend fun getUsers(): Result<List<UserDataModel>, ResultError> {
        return try {
            Result.Success(userDao.getAllUsers())
        } catch (exception: Exception) {
            Result.Error(ResultError.Error)
        }
    }
}