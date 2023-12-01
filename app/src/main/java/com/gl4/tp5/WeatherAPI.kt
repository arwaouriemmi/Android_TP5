package com.gl4.tp5

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {

    @GET("weather")
    fun getWeather(@Query("q") cityName: String, @Query("APPID") apiKey: String): Call<WeatherResponse>
}