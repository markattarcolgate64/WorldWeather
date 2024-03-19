package com.example.worldweather

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.worldweather.ui.theme.WorldWeatherTheme

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.homescreen_layout)

        //Okay so when we create the app we need to have the menu on the top left
        //I'll need to look up a menu app to see how that goes
        //Also we will need a button for the search and locations and then the connection to google maps
        //We will need to get that return value from the search
        //Then after receiving it, an intent will take it and start the CityWeather Fragment activity
        //I need to figure out if those fragments should be loaded from the get go or only after the intent
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

