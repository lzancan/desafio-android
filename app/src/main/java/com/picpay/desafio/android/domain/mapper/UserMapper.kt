package com.picpay.desafio.android.domain.mapper

import com.picpay.desafio.android.data.response.UserResponse
import com.picpay.desafio.android.domain.model.User

object UserMapper {

    fun List<UserResponse>.mapToModel(): List<User> = this.map {
        User(
            img = it.img,
            name = it.name,
            id = it.id,
            username = it.username
        )
    }
}