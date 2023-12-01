package com.gl4.tp5
import android.R
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.gl4.tp5.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private val viewModel: WeatherViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private val cityNames = listOf("Tunisia","Paris", "London", "New York", "Tokyo")

    private var city = "Tunisia";
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        val adapter = ArrayAdapter(this, R.layout.simple_spinner_item, cityNames)
        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)

        binding.citySpinner.adapter = adapter
        viewModel.getWeatherData(city)
        viewModel.weatherData.observe(this) { weatherData ->
            Log.d("MainActivity", "Observer called with weatherData: $weatherData")
            city = weatherData.name
            val weatherCondition =
                weatherData.weather[0].main.toLowerCase()
            val drawableResourceId = resources.getIdentifier(
                weatherCondition, "drawable",
                packageName
            )
            if (drawableResourceId != 0) {
                binding.imageViewWeather.setImageResource(drawableResourceId)
            }

            binding.textViewWeatherDescription.text = weatherData.weather[0].description
            binding.textViewPressure.text = "Pressure: ${weatherData?.main?.pressure} hPa"
            binding.textViewCity.text = weatherData.name
            binding.textViewTemperature.text = "${weatherData?.main?.temp}°C"
            binding.textViewHumidity.text = "Humidity: ${weatherData?.main?.humidity}%"
        }
        binding.citySpinner.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>, selectedItemView: View?, position: Int, id: Long) {
                val selectedCity = cityNames[position]
                viewModel.getWeatherData(selectedCity)
            }

            override fun onNothingSelected(parentView: AdapterView<*>) {
                // Handle when nothing is selected
            }
        })


        binding.openSecondActivityButton.setOnClickListener {
            val intent = Intent(this, WeatherWeekActivity::class.java)
            intent.putExtra("city", city)
            startActivity(intent)
        }
    }




}





