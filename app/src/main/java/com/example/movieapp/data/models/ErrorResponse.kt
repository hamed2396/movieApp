package com.example.movieapp.data.models


import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("status_code")
    var statusCode: Int?, // 6
    @SerializedName("status_message")
    var statusMessage: String?, // Invalid id: The pre-requisite id is invalid or not found.
    @SerializedName("success")
    var success: Boolean? // false
)