package com.example.movieapp.ui.screens.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.models.ResponseSearch
import com.example.movieapp.data.repository.SearchRepository
import com.example.movieapp.utils.network.NetworkResponse
import com.example.movieapp.utils.network.NetworkStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: SearchRepository,
    private val networkResponse: NetworkResponse
) : ViewModel() {

    var movieData by mutableStateOf<NetworkStatus<ResponseSearch>>(NetworkStatus.Idle())
        private set
    var title by mutableStateOf("")
    private var searchJob: Job? = null
    fun onTitleChanged(newTitle: String) {
        title = newTitle

        // Cancel previous job if still waiting
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(500) // debounce time
            if (title.isNotBlank()) {
                callSearchApi(title)
            } else {
                movieData = NetworkStatus.Idle() // optional: reset results if empty
            }
        }
    }

    fun callSearchApi(title: String) = viewModelScope.launch {
        movieData = NetworkStatus.Loading()
        val data = repository.searchMovies(title)
        movieData = networkResponse.handleResponse(data)
    }

}

