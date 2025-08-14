package com.example.movieapp.ui.screens.favorite

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.db.FavoriteEntity
import com.example.movieapp.data.repository.FavoriteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val repository: FavoriteRepository,

    ) : ViewModel() {
    var isFavorite by mutableStateOf(false)

    fun saveFavorite(entity: FavoriteEntity) = viewModelScope.launch {
        Log.e("mytag", "$entity", )
        repository.insertFavorite(entity)
    }

    fun deleteFavorite(entity: FavoriteEntity) = viewModelScope.launch {
        repository.deleteFavorite(entity)
    }

    fun getAllFavorite() = repository.getAlLFavorite()

    fun isMovieInFavorite(id: Int) {
        repository.getAlLFavorite().forEach { movieId->
            Log.e("mytag", "movieId ${movieId.favorite!!.id} searchId $id", )
            if (movieId.favorite!!.id == id) isFavorite = true
        }
    }

}

