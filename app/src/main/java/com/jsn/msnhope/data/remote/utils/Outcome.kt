package com.jsn.msnhope.data.remote.utils

sealed class Outcome<out T> {
    data class Success<out T>(val data: T) : Outcome<T>()
    data class Error(val errorCode: Int, val errorMessage: String) : Outcome<Nothing>()
    object Loading : Outcome<Nothing>()
}
