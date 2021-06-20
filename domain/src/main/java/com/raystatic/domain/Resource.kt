package com.raystatic.domain

import java.lang.Exception

sealed class Resource<out T> {

    data class Success<out T>(val data: T?):Resource<T>()

    data class ERROR(val exception: Exception):Resource<Nothing>()

    object Loading:Resource<Nothing>()

}