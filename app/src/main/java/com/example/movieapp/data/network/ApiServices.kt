package com.example.movieapp.data.network

import com.example.movieapp.data.models.home.ResponseTopRated
import retrofit2.Response
import retrofit2.http.GET

interface ApiServices {
   @GET("movie/top_rated")
   suspend fun getTopRatedMovies(): Response<ResponseTopRated>


}