package com.example.lifecyclesample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i("LifeCycleSample", "Sub onCreate() called.")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val btBack = findViewById<Button>(R.id.btBack)
        btBack.setOnClickListener(BackListener())
    }

    public override fun onStart() {
        Log.i("LifeCycleSample", "Sub onStart() called.")
        super.onStart()
    }

    public override fun onRestart() {
        Log.i("LifeCycleSample", "Sub onRestart() called.")
        super.onRestart()
    }

    public override fun onResume() {
        Log.i("LifeCycleSample", "Sub onResume() called.")
        super.onResume()
    }

    public override fun onPause() {
        Log.i("LifeCycleSample", "Sub onPause() called.")
        super.onPause()
    }

    public override fun onStop() {
        Log.i("LifeCycleSample", "Sub onStop() called.")
        super.onStop()
    }

    public override fun onDestroy() {
        Log.i("LifeCycleSample", "Sub onDestory() called.")
        super.onDestroy()
    }

    inner class BackListener: View.OnClickListener {
        override fun onClick(v: View?) {
            finish()
        }
    }
}