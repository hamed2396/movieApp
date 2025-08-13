package com.example.movieapp.data.repository

import com.example.movieapp.data.network.ApiServices
import javax.inject.Inject

class DetailRepository @Inject constructor(private val apiServices: ApiServices) {

    suspend fun getMovieDetail(movieId: Int) = apiServices.getMovieDetail(movieId)
    suspend fun getMovieActors(movieId: Int) = apiServices.getMovieActors(movieId)

}