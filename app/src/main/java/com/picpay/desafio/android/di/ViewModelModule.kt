package com.picpay.desafio.android.di

import com.picpay.desafio.android.data.PicPayDefaultRepository
import com.picpay.desafio.android.data.datasource.LocalDataSource
import com.picpay.desafio.android.data.datasource.LocalDefaultDataSource
import com.picpay.desafio.android.data.datasource.RemoteDataSource
import com.picpay.desafio.android.data.datasource.RemoteDefaultDataSource
import com.picpay.desafio.android.domain.PicPayRepository
import com.picpay.desafio.android.domain.usecase.GetUsers
import com.picpay.desafio.android.domain.usecase.GetUsersUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class ViewModelModule {

    @Binds
    abstract fun bindGetUsersUseCase(getUsersUseCase: GetUsers): GetUsersUseCase

    @Binds
    abstract fun bindPicPayRepository(repository: PicPayDefaultRepository): PicPayRepository

    @Binds
    abstract fun bindAppLocalDataSource(remoteDataSource: LocalDefaultDataSource): LocalDataSource

    @Binds
    abstract fun bindAppRemoteDataSource(remoteDataSource: RemoteDefaultDataSource): RemoteDataSource
}