package com.example.movieapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.movieapp.ui.screens.favorite.FavoriteConverters


@Database(
    entities = [FavoriteEntity::class,],
    version = 4,
    exportSchema = false
)
@androidx.room.TypeConverters(FavoriteConverters::class)
abstract class AppDataBase : RoomDatabase() {
    abstract fun dao(): FavoriteDao
}