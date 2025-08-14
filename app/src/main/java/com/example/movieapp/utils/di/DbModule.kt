package com.example.movieapp.utils.di

import android.content.Context
import androidx.room.Room
import com.example.movieapp.data.db.AppDataBase
import com.example.movieapp.data.db.FavoriteEntity
import com.example.movieapp.ui.screens.favorite.FavoriteConverters
import com.example.movieapp.utils.Constants

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DbModule {

    @Provides
    fun provideTypeConverterHelper(gson: Gson)= FavoriteConverters(gson)
    @Provides
    @Singleton
    fun provideDb(@ApplicationContext context: Context,typeConverters: FavoriteConverters) =
        Room.databaseBuilder(context, AppDataBase::class.java, Constants.DB_NAME).addTypeConverter(typeConverters)
            .fallbackToDestructiveMigration().allowMainThreadQueries().build()


    @Provides
    @Singleton
    fun provideDao(db: AppDataBase) = db.dao()
    @Provides
    @Singleton
    fun provideEntity() = FavoriteEntity()

}