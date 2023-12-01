package com.gl4.tp5

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper
{
    private const val baseUrl ="https://api.openweathermap.org/data/2.5/"

    private val retrofit = Retrofit.Builder().baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val retrofitService : WeatherAPI by lazy { retrofit.create(WeatherAPI::class.java) }
}