package com.example.movieapp.utils.network

import com.example.movieapp.data.models.home.ResponseGenres
import com.example.movieapp.data.models.home.ResponseTopRated
import com.example.movieapp.data.models.home.ResponseTrending

data class HomeUiState(
    val isLoading: Boolean = false,
    val topRatedMovies: NetworkStatus<ResponseTopRated>? = null,
    val genresList: NetworkStatus<ResponseGenres>? = null,
    val trendingList: NetworkStatus<ResponseTrending>? = null
)