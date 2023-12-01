package com.gl4.tp5
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    private val viewModel: WeatherViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val citySpinner: Spinner = findViewById(R.id.citySpinner)
        val temperatureTextView: TextView = findViewById(R.id.textViewTemperature)
        val humidityTextView: TextView = findViewById(R.id.textViewHumidity)
        val cityNames = listOf("Tunis","Paris", "London", "New York", "Tokyo")

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, cityNames)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        citySpinner.adapter = adapter
        viewModel.getWeatherData("Paris", "17db59488cadcad345211c36304a9266")
        viewModel.weatherData.observe(this) { weatherData ->
            Log.d("MainActivity", "Observer called with weatherData: $weatherData")
            temperatureTextView.text = "Temperature: ${weatherData?.main?.temp}°C"
            humidityTextView.text = "Humidity: ${weatherData?.main?.humidity}%"
        }
        citySpinner.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>, selectedItemView: View?, position: Int, id: Long) {
                val selectedCity = cityNames[position]
                Log.d("ghj","fh")
                viewModel.getWeatherData(selectedCity, "17db59488cadcad345211c36304a9266")
            }

            override fun onNothingSelected(parentView: AdapterView<*>) {
                // Handle when nothing is selected
            }
        })

}


}





