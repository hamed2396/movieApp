package com.example.movieapp.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.movieapp.data.models.detail.ResponseDetail
import com.example.movieapp.utils.Constants


@Entity(Constants.FAVORITE_TABLE)
data class FavoriteEntity(
    @PrimaryKey(autoGenerate = false)
    var tableId: Int?=null,
    var favorite: ResponseDetail?=null,
    val timeAdded: Long = System.currentTimeMillis()

)