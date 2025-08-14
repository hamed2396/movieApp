package com.example.movieapp.data.repository

import com.example.movieapp.data.db.FavoriteDao
import com.example.movieapp.data.db.FavoriteEntity
import com.example.movieapp.data.network.ApiServices
import javax.inject.Inject

class FavoriteRepository @Inject constructor(private val dao: FavoriteDao) {

    suspend fun insertFavorite(entity: FavoriteEntity) = dao.insertFavorite(entity)
    suspend fun deleteFavorite(entity: FavoriteEntity) = dao.deleteFavorite(entity)
    fun getAlLFavorite()=dao.getAllFavorites()

}