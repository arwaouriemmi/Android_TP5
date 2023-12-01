package com.gl4.tp5

import com.gl4.tp5.data.Forecast
import com.gl4.tp5.data.WeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {

    @GET("weather")
    fun getWeather(@Query("q") cityName: String, @Query("APPID") apiKey: String = "17db59488cadcad345211c36304a9266", @Query("units") units: String = "metric"): Call<WeatherResponse>

    @GET("forecast/daily")
    fun getForecast(@Query("q") cityName: String, @Query("APPID") apiKey: String = "17db59488cadcad345211c36304a9266", @Query("units") units: String = "metric"): Call<Forecast>

}