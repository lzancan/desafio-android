package com.picpay.desafio.android.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.picpay.desafio.android.data.datamodel.UserDataModel

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(vararg user: UserDataModel)

    @Query("SELECT * FROM userDataModel")
    fun getAllUsers(): List<UserDataModel>
}