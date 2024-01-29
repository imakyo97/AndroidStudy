package com.example.weatherappsample

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {
    companion object {
        private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
        private const val API_KEY = ""
    }

    // スネークケースをローワーキャメルケースに変換するための設定
    private val gson = GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .create()

    // addConverterFactoryでJsonをObjectに変換するよう設定（Gsonを使用）
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
    private val apiService = retrofit.create(ApiService::class.java)

    suspend fun featchWeather(city: String): WeatherData {
        return apiService.featchWeather(
            API_KEY, "metric", "ja", city,
        )
    }
}