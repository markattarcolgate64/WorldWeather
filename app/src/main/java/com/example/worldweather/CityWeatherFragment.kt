package com.example.worldweather

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

class CityWeatherFragment:  Fragment() {
    private lateinit var cityNameView: TextView
    private lateinit var temperatureView: TextView
    private lateinit var weatherDetailView: TextView

    //Have to move the function likely to the fragment
    companion object {
        private const val TAG = "CityWeatherFragment"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i(TAG, "Entering onCreate")
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i(TAG, "${javaClass.simpleName}::entered onCreateView()")


        return inflater.inflate(R.layout.weather_fragment, container, false)

//        fun onClickSeven() {
//
//
//        }

    }
}



