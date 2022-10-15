package com.picpay.desafio.android.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.domain.model.User
import com.picpay.desafio.android.domain.usecase.GetUsersUseCase
import com.picpay.desafio.android.util.ResultError
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase
): ViewModel() {

    val usersLoaded = MutableLiveData<List<User>>()
    val loadError = MutableLiveData<ResultError>()

    fun fetchData() {
        viewModelScope.launch(Dispatchers.IO) {
            getUsersUseCase().onSuccess {
                usersLoaded.postValue(it)
            }.onError{
                loadError.postValue(it)
            }
        }
    }
}