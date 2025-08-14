package com.example.movieapp.data.repository

import com.example.movieapp.data.network.ApiServices
import javax.inject.Inject

class SearchRepository @Inject constructor(private val apiServices: ApiServices) {

    suspend fun searchMovies(title: String) = apiServices.searchMovies(title)

}