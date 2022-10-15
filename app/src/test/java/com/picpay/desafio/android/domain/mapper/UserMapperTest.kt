package com.picpay.desafio.android.domain.mapper

import com.picpay.desafio.android.UserTestFactory
import com.picpay.desafio.android.domain.mapper.UserMapper.mapDataModelToModel
import com.picpay.desafio.android.domain.mapper.UserMapper.mapResponseToDataModel
import org.junit.Assert
import org.junit.Test

class UserMapperTest {

    @Test
    fun `map from response to data model correctly`() {
        val response = listOf(UserTestFactory.makeUserResponse())
        val expectedDataModel = listOf(UserTestFactory.makeUserDataModel())

        val model = response.mapResponseToDataModel()

        Assert.assertEquals(expectedDataModel.single(), model.single())
    }

    @Test
    fun `map from data model to model correctly`() {
        val response = listOf(UserTestFactory.makeUserDataModel())
        val expectedModel = listOf(UserTestFactory.makeUser())

        val model = response.mapDataModelToModel()

        Assert.assertEquals(expectedModel.single(), model.single())
    }

}