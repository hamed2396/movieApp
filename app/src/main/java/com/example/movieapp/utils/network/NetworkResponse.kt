package com.example.movieapp.utils.network

import com.example.movieapp.data.models.ErrorResponse
import com.example.movieapp.utils.network.NetworkStatus.Data
import com.example.movieapp.utils.network.NetworkStatus.Error
import com.google.gson.Gson
import retrofit2.Response
import javax.inject.Inject

class NetworkResponse @Inject constructor() {

    fun <T> handleResponse(response: Response<T>): NetworkStatus<T> {
        return when {
            response.isSuccessful -> {
                val body = response.body()
                if (body != null) {
                    Data(body)
                } else {
                    Error("Response body is null")
                }
            }
            response.code() == 401 -> Error("You Are Not Authorized")
            response.code() == 422 -> {
                var errorMessage = "Unprocessable Entity"
                response.errorBody()?.let { errorBody ->
                    try {
                        val gson = Gson()
                        val errorResponse = gson.fromJson(
                            errorBody.charStream(),
                            ErrorResponse::class.java
                        )
                        errorMessage = errorResponse.statusMessage ?: errorMessage
                    } catch (_: Exception) {

                    }
                }
                Error(errorMessage)
            }
            response.code() == 500 -> Error("Try Again")
            else -> Error(response.message())
        }
    }
}