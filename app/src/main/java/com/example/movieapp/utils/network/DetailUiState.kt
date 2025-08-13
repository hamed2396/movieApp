package com.example.movieapp.utils.network

import com.example.movieapp.data.models.detail.ResponseDetail
import com.example.movieapp.data.models.detail.ResponseMovieActors
import com.example.movieapp.data.models.home.ResponseTopRated

data class DetailUiState(
    val isLoading: Boolean = false,
    val detailOverView: NetworkStatus<ResponseDetail>? = null,
    val actors: NetworkStatus<ResponseMovieActors>? = null,

    )

