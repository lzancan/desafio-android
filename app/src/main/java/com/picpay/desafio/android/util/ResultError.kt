package com.picpay.desafio.android.util

sealed class ResultError {
    object NetworkError : ResultError()
}