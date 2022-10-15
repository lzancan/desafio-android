package com.picpay.desafio.android.data.datamodel

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserDataModel(
    @PrimaryKey
    val id: Int,
    val img: String,
    val name: String,
    val username: String
)