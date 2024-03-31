package com.example.worldweather

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Adapter
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import androidx.activity.ComponentActivity
import com.androdocs.httprequest.HttpRequest
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.net.PlacesClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject


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
    private var imperial: Boolean = true
    private lateinit var placesClient: PlacesClient
    private lateinit var placesAdapter: Adapter
    private var placesList = ArrayList<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Don't push this to the world
        setContentView(R.layout.homescreen_layout)
//
        if (!Places.isInitialized()){
            Places.initialize(this, com.example.worldweather.BuildConfig.GOOGLE_CLOUD_API_KEY)
        }

        placesClient = Places.createClient(this)
        val apiKOpenWeather = "ca147d186a7f4c4a2c7a22873c1fedb0"
        placesAdapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, placesList)
        searchBox = findViewById(R.id.search_box)
        searchButton = findViewById(R.id.search_button)

        searchButton.setOnClickListener { onSearch() }



    }



//    fun googleAutoComplete(){
//        // Create a new token for the autocomplete session. Pass this to FindAutocompletePredictionsRequest,
//        // and once again when the user makes a selection (for example when calling fetchPlace()).
//        val token = AutocompleteSessionToken.newInstance()
//
//        // Create a RectangularBounds object.
//        val bounds = RectangularBounds.newInstance(
//            LatLng(-33.880490, 151.184363),
//            LatLng(-33.858754, 151.229596)
//        )
//        // Use the builder to create a FindAutocompletePredictionsRequest.
//        val request =
//            FindAutocompletePredictionsRequest.builder()
//                // Call either setLocationBias() OR setLocationRestriction().
//                .setLocationBias(bounds)
//                //.setLocationRestriction(bounds)
//                .setOrigin(LatLng(-33.8749937, 151.2041382))
//                .setTypesFilter(listOf(PlaceTypes.CITIES))
//                .setSessionToken(token)
//                .setQuery(searchBox.text.toString())
//                .build()
//        placesClient.findAutocompletePredictions(request)
//            .addOnSuccessListener { response: FindAutocompletePredictionsResponse ->
//                for (prediction in response.autocompletePredictions) {
//                    Log.i(TAG, prediction.placeId)
//                    Log.i(TAG, prediction.getPrimaryText(null).toString())
//                }
//            }.addOnFailureListener { exception: Exception? ->
//                if (exception is ApiException) {
//                    Log.e(TAG, "Place not found: ${exception.statusCode}")
//                }
//            }
//
//    }
        //Okay so when we create the app we need to have the menu on the top left
        //I'll need to look up a menu app to see how that goes
        //Also we will need a button for the search and locations and then the connection to google maps
        //Then we need to get the google maps locations and put them into an adapter for the autocomplete view
        //We will need to get that return value from the search
        //Then after receiving it, an intent will take it and start the CityWeather Fragment activity
        //I need to figure out if those fragments should be loaded from the get go or only after the intent

    fun onSearch() {
            if (!searchBox.text.isNullOrBlank()) {
                val searchText = searchBox.text.toString()
                var response: String = ""
                val cityName = (searchBox.text).toString()
                val cityCoordinates = geoCode(cityName)
            }


            Log.i(TAG, "Search Error")
        }

    fun geoCode(cityName: String){
        var response: String = ""
        CoroutineScope(Dispatchers.IO).launch {
            response = HttpRequest.excuteGet("https://maps.googleapis.com/maps/api/geocode/json?address=${cityName}&key=${com.example.worldweather.BuildConfig.GOOGLE_CLOUD_API_KEY}")
            val jsonObject = JSONObject(response)
            var lat_lng: JSONObject = jsonObject.getJSONArray("results").getJSONObject(0).getJSONObject("geometry").getJSONObject("location")
            val lat = lat_lng.getDouble("lat")
            val lng = lat_lng.getDouble("lng")

            withContext(Dispatchers.Main){
                onCoordinatesReceived(cityName, lat,lng)
            }

        }


    }

    fun onCoordinatesReceived(cityName: String, lat: Double, lng:Double){
        val weatherIntent = Intent(this,WeatherActivity::class.java)
        weatherIntent.putExtra("City Name", cityName)
        weatherIntent.putExtra("Lat", lat)
        weatherIntent.putExtra("Lng", lng)
        startActivity(weatherIntent)
    }




//Paper DB







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
