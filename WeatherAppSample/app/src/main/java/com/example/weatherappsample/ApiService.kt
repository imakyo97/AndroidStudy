package com.example.weatherappsample

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("weather")
    suspend fun featchWeather(
        @Query("appId") appId: String,
        @Query("units") units: String,
        @Query("lang") lang: String,
        @Query("q") city: String
    ): WeatherData
}