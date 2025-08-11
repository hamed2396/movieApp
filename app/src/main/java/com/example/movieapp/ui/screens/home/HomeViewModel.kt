package com.example.movieapp.ui.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.models.home.ResponseGenres
import com.example.movieapp.data.models.home.ResponseTopRated
import com.example.movieapp.data.repository.HomeRepository
import com.example.movieapp.utils.network.NetworkResponse
import com.example.movieapp.utils.network.NetworkStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HomeRepository,
    private val networkResponse: NetworkResponse
) : ViewModel() {
    init {
        getTopRatedMovie()
        getGenresList()
    }
    var topRatedMovies by mutableStateOf<NetworkStatus<ResponseTopRated>>(NetworkStatus.Loading())
        private set
    var genresList by mutableStateOf<NetworkStatus<ResponseGenres>>(NetworkStatus.Loading())
        private set

    fun getTopRatedMovie() = viewModelScope.launch {
        val callApi = repository.getTopRatedMovies()
        val result = networkResponse.handleResponse(callApi)
        topRatedMovies=result
    }
    fun getGenresList() = viewModelScope.launch {
        val callApi = repository.getGenresList()
        val result = networkResponse.handleResponse(callApi)
        genresList=result
    }
}