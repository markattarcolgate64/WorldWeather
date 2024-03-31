package com.example.worldweather
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.ui.text.capitalize
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import com.androdocs.httprequest.HttpRequest
import dagger.Component
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.lang.Error

class WeatherActivity: ComponentActivity() {
//May need to delete persistent state
    private val apiKOpenWeather = BuildConfig.WEATHER_API_KEY//"ca147d186a7f4c4a2c7a22873c1fedb0"
    private var TAG = "Weather Activity"
    private lateinit var cityNameView: TextView
    private lateinit var temperatureView: TextView
    private lateinit var weatherDetailView: TextView
    private lateinit var highLowView: TextView
    private lateinit var weatherView: LinearLayout
    private var imperialUnits: Boolean = true
    private var temp: Int = 0
    private var high: Int = 0
    private var low: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.weather_fragment)
        Log.i(TAG, "Entered the oncreate")
        weatherView = findViewById(R.id.weather_fragment)

        cityNameView =  findViewById(R.id.cityName)
        temperatureView = findViewById(R.id.temperature)
        weatherDetailView = findViewById(R.id.weather_description)
        highLowView = findViewById(R.id.high_low)
        temperatureView = findViewById(R.id.temperature)

        val cityName = intent.getStringExtra("City Name")
        val lat = intent.getDoubleExtra("Lat", 0.0)
        val lng = intent.getDoubleExtra("Lng", 0.0)

        if ((!cityName.isNullOrBlank()) || (lat != 0.0) || (lng != 0.0)){
            cityNameView.text = cityName
        } else{
            Log.i(TAG, "INTENT DATA PASSING BROKEN")
        }

        fetchWeatherData(lat,lng)

    }

    fun fetchWeatherData(lat: Double, lon: Double) {
        var response: String = ""
        CoroutineScope(Dispatchers.IO).launch {
            //              val geoCode=  HttpRequest.excuteGet("http://api.openweathermap.org/geo/1.0/direct?q=${CITY}&limit={limit}&appid={API key}")
            //Log.i(TAG, geoCode)
//Write another function to break out the response when we have time
            try {
                response =
                    HttpRequest.excuteGet("https://api.openweathermap.org/data/2.5/weather?lat=${lat}&lon=${lon}&appid=${apiKOpenWeather}")

                withContext(Dispatchers.Main) {
                    //Change the form of the information from response to get what we want and then
//                    //If it is seven then change the target of the sent information

                    val jsonObject = JSONObject(response)
                    temp = jsonObject.getJSONObject("main").getInt("temp")
                    temperatureView.text = getString(R.string.temp, convertUnits(temp,imperialUnits))
                    checkTempImage()
                    high = jsonObject.getJSONObject("main").getInt("temp_max")
                    low = jsonObject.getJSONObject("main").getInt("temp_min")
                    val highLow = getString(R.string.highLow, convertUnits(high, imperialUnits),convertUnits(low, imperialUnits))
                    highLowView.text = highLow
                    var weatherDetail = jsonObject.getJSONArray("weather").getJSONObject(0).get("description")
                    val weatherStr = weatherDetail.toString().split(" ").joinToString(" ") { word ->
                        word.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
                    }
                    weatherDetailView.text = weatherStr

                }
            } catch (e: Error) {
                Log.i(TAG, "${e}")
            }
        }  //Code that takes forecast for just one day

        //Starts intent for fragment or sends the object concerning the fragment to b
    }

    fun convertUnits(num: Int, units:Boolean): Int{
        var newTemp = 0.0
        if (units == true){
            //Fahrenheit
            newTemp = (num - 273.15) * 9/5 + 32
        }
        else{
            //Celsius
            newTemp = (num - 273.15) * 9/5 + 32

        }
        return newTemp.toInt()
    }

    fun checkTempImage(){
        val checkTemp = convertUnits(temp, true)
        var weatherDetail: String = weatherDetailView.text.toString()
        if (weatherDetail.contains("rainy", ignoreCase = true)) {
            weatherView.background = ContextCompat.getDrawable(this, R.drawable.firefly_very_simple__rainy_background_for_mobile_weather_app_87031)
        }
        else if (checkTemp > 70){
            weatherView.background = ContextCompat.getDrawable(this, R.drawable.firefly_sunny_sky_background_for_mobile_weather_app_87031)
        }

    }





}