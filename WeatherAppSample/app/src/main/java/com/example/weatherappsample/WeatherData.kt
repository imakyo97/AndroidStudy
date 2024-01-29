package com.example.weatherappsample

data class WeatherData(
    val weather: List<Weather>,
    val main: Main,
    val name: String,
)

data class Weather(
    val description: String,
    val icon: String,
)

data class Main(
    val tempMin: Double,
    val tempMax: Double,
)

