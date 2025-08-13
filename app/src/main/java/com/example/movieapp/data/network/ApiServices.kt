package com.example.movieapp.data.network

import com.example.movieapp.data.models.detail.ResponseDetail
import com.example.movieapp.data.models.detail.ResponseMovieActors
import com.example.movieapp.data.models.home.ResponseGenres
import com.example.movieapp.data.models.home.ResponseTopRated
import com.example.movieapp.data.models.home.ResponseTrending
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiServices {
    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(): Response<ResponseTopRated>

    @GET("genre/movie/list")
    suspend fun getGenresList(): Response<ResponseGenres>

    @GET("trending/movie/week")
    suspend fun getTrendingMovies(): Response<ResponseTrending>

    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(@Path("movie_id") movieId: Int): Response<ResponseDetail>
    @GET("movie/{movie_id}/credits")
    suspend fun getMovieActors(@Path("movie_id") movieId: Int): Response<ResponseMovieActors>


}