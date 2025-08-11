package com.example.movieapp.ui.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.models.home.ResponseGenres
import com.example.movieapp.data.models.home.ResponseTopRated
import com.example.movieapp.data.repository.HomeRepository
import com.example.movieapp.utils.network.HomeUiState
import com.example.movieapp.utils.network.NetworkResponse
import com.example.movieapp.utils.network.NetworkStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HomeRepository,
    private val networkResponse: NetworkResponse
) : ViewModel() {

    var uiState by mutableStateOf(HomeUiState())
        private set

    init {
        loadHomeData()
    }

    private fun loadHomeData() {
        uiState = uiState.copy(isLoading = true)

        viewModelScope.launch {
            val topRatedDeferred = async { repository.getTopRatedMovies() }
            val genresDeferred = async { repository.getGenresList() }

            val topRatedResult = networkResponse.handleResponse(topRatedDeferred.await())
            val genresResult = networkResponse.handleResponse(genresDeferred.await())

            uiState = uiState.copy(
                isLoading = false,
                topRatedMovies = topRatedResult,
                genresList = genresResult
            )
        }
    }
}

