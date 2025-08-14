package com.example.movieapp.ui.screens.favorite

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.movieapp.data.models.detail.ResponseDetail
import com.google.gson.Gson
import javax.inject.Inject

@ProvidedTypeConverter
class FavoriteConverters @Inject constructor(private val gson: Gson) {

    @TypeConverter
    fun movieToJson(movie: ResponseDetail): String = gson.toJson(movie)

    @TypeConverter
    fun stringToMovie(data: String): ResponseDetail =
        gson.fromJson(data, ResponseDetail::class.java)


}