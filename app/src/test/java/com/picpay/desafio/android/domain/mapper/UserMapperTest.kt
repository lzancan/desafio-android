package com.picpay.desafio.android.domain.mapper

import com.picpay.desafio.android.domain.mapper.UserMapper.mapToModel
import org.junit.Assert
import org.junit.Test

class UserMapperTest {

    @Test
    fun `map from response to model correctly`() {
        val response = listOf(UserTestFactory.makeUserResponse())
        val expectedModel = listOf(UserTestFactory.makeUser())

        val model = response.mapToModel()

        Assert.assertEquals(expectedModel.single(), model.single())
    }

}