package com.example.weatherappsample

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.weatherappsample.databinding.ActivityMainBinding
import java.io.IOException
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    private lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.etCity.setOnEditorActionListener(OnCityEditorActionListener())
    }

    private inner class OnCityEditorActionListener : TextView.OnEditorActionListener {
        override fun onEditorAction(p0: TextView?, p1: Int, p2: KeyEvent?): Boolean {
            if (p1 == EditorInfo.IME_ACTION_DONE) {
                executeFetchWeather(p0?.text.toString())
                return true
            }
            return false
        }

    }

    private fun executeFetchWeather(city: String) {
        // Call<T>は以下のような感じで処理する
        // TODO: あとでCall<T>について調べる
//        thread {
//            runCatching { ApiClient().fetchWeather(city).execute() }
//                .onSuccess { response ->
//                    if (response.isSuccessful) {
//                        response.body().let {
//                            Log.d("DEBUG", "$it")
//                        }
//                    } else {
//                        val msg = "HTTP error. HTTP status code: ${response.code()}"
//                        Log.d("DEBUG", msg)
//                    }
//                }
//                .onFailure { t -> Log.e("DEBUG", t.toString()) }
//        }

        // このコードでも動きそう
        thread {
            try {
                val response = ApiClient().fetchWeather(city).execute()
                if (response.isSuccessful) {
                    response.body().let {
                        Log.d("DEBUG", "$it")
                    }
                } else {
                    val msg = response.message()
                    Log.d("DEBUG", msg)
                }
            } catch (error: IOException) {
                Log.d("DEBUG", error.toString())
            }
        }
    }
}