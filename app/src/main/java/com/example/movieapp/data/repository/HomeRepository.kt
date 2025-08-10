package com.example.movieapp.data.repository

import com.example.movieapp.data.network.ApiServices
import javax.inject.Inject

class HomeRepository@Inject constructor(private val apiServices: ApiServices) {
    suspend fun getTopRatedMovies()=apiServices.getTopRatedMovies()
}