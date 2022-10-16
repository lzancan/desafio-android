package com.picpay.desafio.android.di

import com.picpay.desafio.android.di.ViewModelModule
import com.picpay.desafio.android.domain.usecase.FakeGetUsersUseCase
import com.picpay.desafio.android.domain.usecase.GetUsersUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [ViewModelModule::class]
)
interface FakeViewModelModule {
    @Binds
    @Singleton
    fun provideGetUsersUseCase(fakeGetUsersUseCase: FakeGetUsersUseCase): GetUsersUseCase
}