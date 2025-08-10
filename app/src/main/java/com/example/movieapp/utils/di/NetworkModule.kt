package com.example.movieapp.utils.di

import com.example.movieapp.data.network.ApiServices
import com.example.movieapp.utils.Constants
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideBaseUrl() = Constants.BASE_URL

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().setLenient().create()

    @Provides
    @Singleton
    fun provideConnectionTimeout() = Constants.CONNECTION_TIME_OUT

    @Provides
    @Singleton
    @Named(Constants.PING_NAMED)
    fun providePingTimeout() = Constants.PING_TIME_OUT

    @Provides
    @Singleton
    fun provideInterceptor() = HttpLoggingInterceptor().apply {
        level =
            HttpLoggingInterceptor.Level.BODY

    }

    @Provides
    @Singleton
    fun provideClient(
        @Named(Constants.PING_NAMED) ping: Long,
        connectionTimeout: Long,
        interceptor: HttpLoggingInterceptor,

    ) =
        OkHttpClient.Builder().addInterceptor { chain ->


            chain.proceed(chain.request().newBuilder().apply {
                addHeader(Constants.AUTHORIZATION, Constants.ACCESS_TOKEN)

            }.build())
        }.apply {
            addInterceptor(interceptor)
        }
            .readTimeout(connectionTimeout, TimeUnit.SECONDS)
            .connectTimeout(connectionTimeout, TimeUnit.SECONDS)
            .writeTimeout(connectionTimeout, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .pingInterval(ping, TimeUnit.SECONDS).build()

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient, baseUrl: String, gson: Gson): ApiServices =
        Retrofit.Builder().baseUrl(baseUrl).client(client)
            .addConverterFactory(GsonConverterFactory.create(gson)).build()
            .create(ApiServices::class.java)
}