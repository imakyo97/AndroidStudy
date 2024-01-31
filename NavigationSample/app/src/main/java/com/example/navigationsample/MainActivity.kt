package com.example.navigationsample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.navigationsample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}

// TODO: アニメーションは（https://stackoverflow.com/questions/21026409/fragment-transaction-animation-slide-in-and-slide-out）からもらった
