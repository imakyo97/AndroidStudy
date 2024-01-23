package com.example.lifecyclesample

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btNext = findViewById<Button>(R.id.btNext)
        btNext.setOnClickListener(NextListener())
    }

    private inner class NextListener: View.OnClickListener {
        override fun onClick(view: View?) {
            val intent = Intent(this@MainActivity, SecondActivity::class.java)
            startActivity(intent)
        }

    }
}
