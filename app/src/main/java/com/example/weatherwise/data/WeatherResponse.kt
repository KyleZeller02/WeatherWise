package com.example.weatherwise.data

import com.google.gson.annotations.SerializedName

/**
 * Data model for the OpenWeather API response.
 */
data class WeatherResponse(
    @SerializedName("name") val city: String,
    @SerializedName("main") val main: Main,
    @SerializedName("weather") val weather: List<WeatherDescription>
)

data class Main(
    @SerializedName("temp") val temp: Float
)

data class WeatherDescription(
    @SerializedName("description") val description: String
)
