package com.gl4.tp5

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gl4.tp5.data.Forecast
import com.gl4.tp5.data.WeatherResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class WeatherViewModel : ViewModel() {

    private val _weatherData = MutableLiveData<WeatherResponse>()
    val weatherData: LiveData<WeatherResponse> get() = _weatherData

    private val _forcastData = MutableLiveData<Forecast>()
    val forecastData: LiveData<Forecast> get() = _forcastData

    fun getWeatherData(cityName: String) {
        val call = RetrofitHelper.retrofitService.getWeather(cityName)
        call.enqueue(object : Callback<WeatherResponse> {
            override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {
                if (response.isSuccessful) {
                    _weatherData.postValue(response.body())

                } else {

                }
            }
            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                Log.e("Error", "API Call Failed", t)
            }
        })
    }

    fun getWeatherForecast(cityName: String) {
        val call = RetrofitHelper.retrofitService.getForecast(cityName)
        call.enqueue(object : Callback<Forecast> {
            override fun onResponse(call: Call<Forecast>, response: Response<Forecast>) {
                if (response.isSuccessful) {
                    _forcastData.postValue(response.body())

                } else {

                }
            }
            override fun onFailure(call: Call<Forecast>, t: Throwable) {
                Log.e("Error", "API Call Failed", t)
            }
        })
    }
}
