package com.example.weatherappsample

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.weatherappsample.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var activityMainBinding: ActivityMainBinding

    private val apiClient = ApiClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.etCity.setOnEditorActionListener(OnCityEditorActionListener())
    }

    private inner class OnCityEditorActionListener : TextView.OnEditorActionListener {
        override fun onEditorAction(p0: TextView?, p1: Int, p2: KeyEvent?): Boolean {
            if (p1 == EditorInfo.IME_ACTION_DONE) {
                lifecycleScope.launch {
                    showProgressBar()
                    kotlin.runCatching { apiClient.fetchWeather(p0?.text.toString()) }
                        .onSuccess { weatherData ->
                            hideProgressBar()
                            showWeatherData(weatherData)
                        }
                        .onFailure { error ->
                            hideProgressBar()
                            Log.d("DEBUG", "error: $error")
                        }
                }
                return false
            }
            return false
        }

        private fun showWeatherData(data: WeatherData) {
            activityMainBinding.tvCity.text = data.name
            activityMainBinding.imageView.setImageResource(getIconResource(data.weather.first().icon))
            activityMainBinding.tvWeather.text = data.weather.first().description
            activityMainBinding.tvTempMax.text = "${data.main.tempMax} ℃"
            activityMainBinding.tvTempMin.text = "${data.main.tempMin} ℃"
        }

        private fun getIconResource(icon: String): Int {
            return when (icon) {
                "01d" -> R.drawable.d01
                "01n" -> R.drawable.n01
                "02d" -> R.drawable.d02
                "02n" -> R.drawable.n02
                "03d" -> R.drawable.d03
                "03n" -> R.drawable.n03
                "04d" -> R.drawable.d04
                "04n" -> R.drawable.n04
                "09d" -> R.drawable.d09
                "09n" -> R.drawable.n09
                "10d" -> R.drawable.d10
                "10n" -> R.drawable.n10
                "11d" -> R.drawable.d11
                "11n" -> R.drawable.n11
                "13d" -> R.drawable.d13
                "13n" -> R.drawable.n13
                "50d" -> R.drawable.d50
                "50n" -> R.drawable.n50
                else -> R.drawable.d01
            }
        }

        private fun showProgressBar() {
            val progressBar = activityMainBinding.progressBar
            progressBar.visibility = View.VISIBLE
        }

        private fun hideProgressBar() {
            val progressBar = activityMainBinding.progressBar
            progressBar.visibility = View.INVISIBLE
        }
    }
}