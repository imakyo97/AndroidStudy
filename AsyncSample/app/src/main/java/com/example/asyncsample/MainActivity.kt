package com.example.asyncsample

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.SimpleAdapter
import android.widget.TextView
import androidx.annotation.MainThread
import androidx.annotation.UiThread
import androidx.annotation.WorkerThread
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.HandlerCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.SocketTimeoutException
import java.net.URL
import java.nio.charset.StandardCharsets
import java.util.concurrent.Executors


class MainActivity : AppCompatActivity() {
    companion object {
        private const val DEBUG_TAG = "AsyncSample"
        private const val WEATHERINFO_URL =
            "https://api.openweathermap.org/data/2.5/weather?lang=ja"
        private const val APP_ID = ""
    }

    private var _list: MutableList<MutableMap<String, String>> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        _list = createList()

        val lvCityList = findViewById<ListView>(R.id.lvCityList)
        val from = arrayOf("name")
        val to = intArrayOf(android.R.id.text1)
        val adapter =
            SimpleAdapter(this@MainActivity, _list, android.R.layout.simple_list_item_1, from, to)
        lvCityList.adapter = adapter
        lvCityList.onItemClickListener = ListItemClickListener()
    }

    private fun createList(): MutableList<MutableMap<String, String>> {
        val list: MutableList<MutableMap<String, String>> = mutableListOf()
        var city = mutableMapOf<String, String>("name" to "大阪", "q" to "Osaka")
        list.add(city)
        city = mutableMapOf<String, String>("name" to "神戸", "q" to "Kobe")
        list.add(city)
        city = mutableMapOf<String, String>("name" to "東京", "q" to "Tokyo")
        list.add(city)
        city = mutableMapOf<String, String>("name" to "名古屋", "q" to "Nagoya")
        list.add(city)
        city = mutableMapOf<String, String>("name" to "福岡", "q" to "Fukuoka")
        list.add(city)
        city = mutableMapOf<String, String>("name" to "北海道", "q" to "Hokaido")
        list.add(city)
        return list
    }

    @UiThread
    private fun receiveWeatherInfo(urlFull: String) {
        lifecycleScope.launch {
            val result = weatherInfoBackgroundRunner(urlFull)
            showWeatherInfo(result)
        }
    }

    @UiThread
    fun showWeatherInfo(result: String) {
        val rootJSON = JSONObject(result)
        val cityName = rootJSON.getString("name")
        val coordJson = rootJSON.getJSONObject("coord")
        val latitude = coordJson.getString("lat")
        val longitude = coordJson.getString("lon")
        val weatherJSONArray = rootJSON.getJSONArray("weather")
        val weatherJSON = weatherJSONArray.getJSONObject(0)
        val weather = weatherJSON.getString("description")
        val telop = "${cityName}の天気"
        val desc = "現在は${weather}です。\n緯度は${latitude}度で軽度は${longitude}度です。"
        val tvWeatherTelop = findViewById<TextView>(R.id.tvWeatherTelop)
        val tvWeatherDesc = findViewById<TextView>(R.id.tvWeatherDesc)
        tvWeatherTelop.text = telop
        tvWeatherDesc.text = desc
    }

    @WorkerThread
    private suspend fun weatherInfoBackgroundRunner(url: String): String {
        val returnVal = withContext(Dispatchers.IO) {
            var result = ""
            val url = URL(url)
            val con = url.openConnection() as HttpURLConnection
            con.connectTimeout = 1000
            con.readTimeout = 1000
            con.requestMethod = "GET"
            try {
                con.connect()
                val stream = con.inputStream
                result = is2String(stream)
                stream.close()
            } catch (ex: SocketTimeoutException) {
                Log.w(DEBUG_TAG, "通信タイムアウト", ex)
            }
            con.disconnect()
            result
        }
        return returnVal
    }


    private fun is2String(stream: InputStream?): String {
        val sb = StringBuilder()
        val reader = BufferedReader(InputStreamReader(stream, StandardCharsets.UTF_8))
        var line = reader.readLine()
        while (line != null) {
            sb.append(line)
            line = reader.readLine()
        }
        reader.close()
        return sb.toString()
    }

    private inner class ListItemClickListener : AdapterView.OnItemClickListener {
        override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            val item = _list.get(position)
            val q = item?.get("q")
            q.let {
                val urlFull = "$WEATHERINFO_URL&q=$it&appid=$APP_ID"
                receiveWeatherInfo(urlFull)
            }
        }
    }
}
