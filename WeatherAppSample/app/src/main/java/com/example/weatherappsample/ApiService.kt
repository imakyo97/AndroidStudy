package com.example.weatherappsample

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("weather")
    fun fetchWeather(
        @Query("appId") appId: String,
        @Query("units") units: String,
        @Query("lang") lang: String,
        @Query("q") city: String
    ): Call<WeatherData>
}