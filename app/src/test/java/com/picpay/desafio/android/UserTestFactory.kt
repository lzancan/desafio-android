package com.picpay.desafio.android

import com.picpay.desafio.android.data.datamodel.UserDataModel
import com.picpay.desafio.android.data.response.UserResponse
import com.picpay.desafio.android.domain.model.User


object UserTestFactory {

    fun makeUserResponse(
        img: String = "img.com",
        name: String = "name",
        id: Int = 1,
        username: String = "username"
    ) = UserResponse(
        img = img,
        name = name,
        id = id,
        username = username
    )

    fun makeUserDataModel(
        img: String = "img.com",
        name: String = "name",
        id: Int = 1,
        username: String = "username"
    ) = UserDataModel(
        img = img,
        name = name,
        id = id,
        username = username
    )

    fun makeUser(
        img: String = "img.com",
        name: String = "name",
        id: Int = 1,
        username: String = "username"
    ) = User(
        img = img,
        name = name,
        id = id,
        username = username
    )
}