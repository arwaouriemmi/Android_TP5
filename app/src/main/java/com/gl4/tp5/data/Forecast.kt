package com.gl4.tp5.data

data class Forecast(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<WeatherList>,
    val message: Double
)