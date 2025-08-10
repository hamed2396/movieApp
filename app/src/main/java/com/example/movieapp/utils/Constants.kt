package com.example.movieapp.utils

import androidx.compose.ui.graphics.Color

object Constants {
    const val USER_NAME = "user_name"
    const val USER_EMAIL = "user_email"
    const val USER_DATA = "user_data"
    const val ACCESS_TOKEN =
        "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxYTUzNDg3N2FjN2IzZGY0YjQ1M2YxYTI0NzJlYTQxMiIsIm5iZiI6MTY4NTU1MDQ5MS4wNzIsInN1YiI6IjY0Nzc3NTliMDc2Y2U4MDBjNTBhNzcyOSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.cI1XNiA-znYicmGZyOr-ocuSgZ7jFMB3kHroRyol4Dg"

    const val BASE_URL = "https://api.themoviedb.org/3/"
    const val AUTHORIZATION = "Authorization: Bearer "
    const val CONNECTION_TIME_OUT = 60L
    const val PING_TIME_OUT = 60L
    const val PING_NAMED = "ping_named"
}
typealias androidColors = Color