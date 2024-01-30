package com.example.weatherappsample

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("weather")
    suspend fun fetchWeather(
        @Query("appId") appId: String,
        @Query("units") units: String,
        @Query("lang") lang: String,
        @Query("q") city: String
    ): Response<WeatherData>
}