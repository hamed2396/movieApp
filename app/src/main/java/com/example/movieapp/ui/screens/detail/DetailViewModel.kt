package com.example.movieapp.ui.screens.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.models.home.ResponseGenres
import com.example.movieapp.data.models.home.ResponseTopRated
import com.example.movieapp.data.repository.DetailRepository
import com.example.movieapp.data.repository.HomeRepository
import com.example.movieapp.utils.network.DetailUiState
import com.example.movieapp.utils.network.HomeUiState
import com.example.movieapp.utils.network.NetworkResponse
import com.example.movieapp.utils.network.NetworkStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: DetailRepository,
    private val networkResponse: NetworkResponse
) : ViewModel() {

    var uiState by mutableStateOf(DetailUiState())
        private set



     fun loadHomeData(movieId:Int) {
        uiState = uiState.copy(isLoading = true)

        viewModelScope.launch {
            val detailDeferred = async { repository.getMovieDetail(movieId) }
            val actorsDeferred = async { repository.getMovieActors(movieId) }


            val detailResult = networkResponse.handleResponse(detailDeferred.await())
            val actorsResult = networkResponse.handleResponse(actorsDeferred.await())



            uiState = uiState.copy(
                isLoading = false,
                detailOverView = detailResult,
                actors = actorsResult,

            )
        }
    }
}

