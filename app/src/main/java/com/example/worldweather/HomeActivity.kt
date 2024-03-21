package com.example.worldweather

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.ParcelFileDescriptor.AutoCloseOutputStream
import android.util.Log
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.androdocs.httprequest.HttpRequest
import com.example.worldweather.ui.theme.WorldWeatherTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory



//Total app design
//Home screen w/ search bar and search
//Toggle button that switches dark/light
//Weather API connection
//Fragment that shows current forecast (temperature, humidity, general weather).
//Image to match current weather
//7 day forecast fragment

class HomeActivity : ComponentActivity() {
    companion object{
        private const val TAG = "HomeActivity"

    }
    private lateinit var searchBox: AutoCompleteTextView
    private lateinit var searchButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Don't push this to the world


//        searchBox = findViewById(R.id.search_box)
//        searchButton = findViewById(R.id.search_button)
        setContentView(R.layout.homescreen_layout)
        val wTask = WeatherTask("London", apiKOpenWeather)

        val weatherResponse = wTask.fetchWeatherData(false)


        //Okay so when we create the app we need to have the menu on the top left
        //I'll need to look up a menu app to see how that goes
        //Also we will need a button for the search and locations and then the connection to google maps
        //Then we need to get the google maps locations and put them into an adapter for the autocomplete view
        //We will need to get that return value from the search
        //Then after receiving it, an intent will take it and start the CityWeather Fragment activity
        //I need to figure out if those fragments should be loaded from the get go or only after the intent
    }

    class WeatherResponse{

    }
    class WeatherTask(private var CITY: String, private var API: String){

        //I need a function to conduct the HTTP request to the API and the other to take the key elements out of the data
        fun fetchWeatherData(seven:Boolean){
            var response: Unit
            response = Unit
            CoroutineScope(Dispatchers.IO).launch {

                val response = try{
                    //val geoCode=  HttpRequest.excuteGet("http://api.openweathermap.org/geo/1.0/direct?q=${CITY}&limit={limit}&appid={API key}")
                    //Log.i(TAG, geoCode)
                    val weatherStr = HttpRequest.excuteGet("https://api.openweathermap.org/data/2.5/weather?lat=${44.34}&lon=${10.99}&appid=${apiKOpenWeather}")

                    Log.i(TAG, weatherStr ?: "Default if null")

                } catch(e: Error){
                    Log.i(TAG, "REQUEST FAILED")
                }

//                 withContext(Dispatchers.Main){
//                                    //Change the form of the information from response to get what we want and then
//                                    if(seven == false) {
//                                        //Code that takes forecast for just one day
//                                    }
//                                    else{
//                                        //Code that takes data for seven day forecast
//                                    }
//                                    //Starts intent for fragment or sends the object concerning the fragment to b
//                                }

            }



            return response
        }

        fun conductRequest(){
//44.34
            //10.99
            val response = try{
                //val geoCode=  HttpRequest.excuteGet("http://api.openweathermap.org/geo/1.0/direct?q=${CITY}&limit={limit}&appid={API key}")
                //Log.i(TAG, geoCode)
                val weatherStr = HttpRequest.excuteGet("https://api.openweathermap.org/data/2.5/weather?lat=${44.34}&lon=${10.99}&appid=${API}")
            } catch(e: Error){
                Log.i(TAG, "REQUEST FAILED")

            }

        }

    }

    override fun onDestroy() {
        Log.i(TAG, "${javaClass.simpleName}::entered onDestroy()")
        super.onDestroy()
    }

    override fun onPause() {
        Log.i(TAG, "${javaClass.simpleName}::entered onPause()")
        super.onPause()
    }

    override fun onRestart() {
        Log.i(TAG, "${javaClass.simpleName}::entered onRestart()")
        super.onRestart()
    }

    override fun onResume() {
        Log.i(TAG, "${javaClass.simpleName}::entered onResume()")
        super.onResume()
    }

    override fun onStart() {
        Log.i(TAG, "${javaClass.simpleName}::entered onStart()")
        super.onStart()
    }

    override fun onStop() {
        Log.i(TAG, "${javaClass.simpleName}::entered onStop()")
        super.onStop()
    }
}

