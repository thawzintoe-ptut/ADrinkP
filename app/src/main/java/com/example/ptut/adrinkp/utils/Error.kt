package com.example.ptut.adrinkp.utils

sealed class Error
data class EmptyError(val msg: String): Error()
data class NetworkError(val msg: String): Error()
data class ServerError(val msg: String): Error()