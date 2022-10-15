package com.picpay.desafio.android.domain.mapper

import com.picpay.desafio.android.data.datamodel.UserDataModel
import com.picpay.desafio.android.data.response.UserResponse
import com.picpay.desafio.android.domain.model.User

object UserMapper {

    fun List<UserResponse>.mapResponseToDataModel(): List<UserDataModel> = this.map {
        UserDataModel(
            img = it.img,
            name = it.name,
            id = it.id,
            username = it.username
        )
    }

    fun List<UserDataModel>.mapDataModelToModel(): List<User> = this.map {
        User(
            img = it.img,
            name = it.name,
            id = it.id,
            username = it.username
        )
    }
}