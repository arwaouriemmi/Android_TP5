package com.gl4.tp5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.MaterialToolbar

class WeatherWeekActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var weatherAdapter: WeatherAdapter
    private val viewModel: WeatherViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_week)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val city = intent.getStringExtra("city") ?: "Tunisia"
        val topAppBar = findViewById<MaterialToolbar>(R.id.topAppBar)

        topAppBar.title = city + " Forecast"

        topAppBar.setNavigationOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


        viewModel.getWeatherForecast(city)
        viewModel.forecastData.observe(this) { weatherData ->
            weatherAdapter = WeatherAdapter(this, weatherData.list)
            recyclerView.adapter = weatherAdapter
        }
    }
}