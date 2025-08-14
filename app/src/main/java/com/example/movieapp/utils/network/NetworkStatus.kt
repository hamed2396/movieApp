package com.example.movieapp.utils.network

sealed class NetworkStatus<T>( val data: T? = null,  val error: String? = null) {
    class Loading<T> : NetworkStatus<T>()
    class Idle<T> : NetworkStatus<T>()
    class Data<T>(data: T) : NetworkStatus<T>(data)
    class Error<T>(message: String) : NetworkStatus<T>(error = message)

}