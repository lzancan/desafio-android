package com.picpay.desafio.android.data.datasource

import com.picpay.desafio.android.data.datamodel.UserDataModel
import com.picpay.desafio.android.util.Result
import com.picpay.desafio.android.util.ResultError

interface LocalDataSource {
    suspend fun insertUsers(users: Array<UserDataModel>)
    suspend fun getUsers(): Result<List<UserDataModel>, ResultError>
}