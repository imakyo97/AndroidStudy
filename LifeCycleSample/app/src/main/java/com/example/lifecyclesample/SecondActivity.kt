package com.example.lifecyclesample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val btBack = findViewById<Button>(R.id.btBack)
        btBack.setOnClickListener(BackListener())
    }

    inner class BackListener: View.OnClickListener {
        override fun onClick(v: View?) {
            finish()
        }
    }
}