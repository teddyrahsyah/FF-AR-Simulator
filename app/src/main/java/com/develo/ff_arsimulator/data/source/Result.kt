package com.develo.ff_arsimulator.data.source

sealed class Result<out R> private constructor() {
    data class Success<out T>(val data: T) : Result<T>()
    data class PostSuccess(val message: String) : Result<Nothing>()
    data class Error(val error: String) : Result<Nothing>()
    object Loading : Result<Nothing>()
}