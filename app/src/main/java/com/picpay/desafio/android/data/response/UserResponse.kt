package com.picpay.desafio.android.data.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserResponse(
    val img: String,
    val name: String,
    val id: Int,
    val username: String
)