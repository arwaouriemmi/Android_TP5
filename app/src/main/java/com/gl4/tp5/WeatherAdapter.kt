package com.gl4.tp5

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gl4.tp5.data.WeatherList
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import android.content.Context

class WeatherAdapter(private val context: Context, weatherList: List<WeatherList>) :
    RecyclerView.Adapter<WeatherAdapter.ViewHolder>() {
    private val weatherList: List<WeatherList>

    init {
        this.weatherList = weatherList
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var weatherImageView: ImageView
        var dateTextView: TextView
        var temperatureTextView: TextView
        var pressureTextView: TextView
        var weatherDescriptionTextView: TextView

        init {
            weatherImageView = itemView.findViewById<ImageView>(R.id.weatherImageView)
            dateTextView = itemView.findViewById<TextView>(R.id.dateTextView)
            temperatureTextView = itemView.findViewById<TextView>(R.id.temperatureTextView)
            pressureTextView = itemView.findViewById(R.id.pressureTextView)
            weatherDescriptionTextView =
                itemView.findViewById(R.id.weatherDescriptionTextView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.weather_item, parent, false)
        return ViewHolder(view)
    }

    fun getDate(unixTimestamp: Long): String {
        val milliseconds = unixTimestamp * 1000
        val date = Date(milliseconds)
        val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        return sdf.format(date)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val weatherData: WeatherList = weatherList[position]

        val imageResId: Int = context.resources.getIdentifier(weatherData.weather[0].main.toLowerCase(), "drawable", context.packageName)
        holder.weatherImageView.setImageResource(imageResId)
        holder.dateTextView.text = getDate(weatherData.dt)
        holder.temperatureTextView.text = "Temperature: " + weatherData.temp.min + "°C to " + weatherData.temp.max +"°C"
        holder.pressureTextView.text = "Pressure: " + weatherData.pressure + " hPa"
        holder.weatherDescriptionTextView.text = weatherData.weather[0].description
    }

    override fun getItemCount(): Int {
        return weatherList.size
    }
}