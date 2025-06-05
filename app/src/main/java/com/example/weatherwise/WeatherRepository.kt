package com.example.weatherwise

import com.example.weatherwise.data.WeatherResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"

interface WeatherService {
    @GET("weather")
    suspend fun getCurrentWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("units") units: String = "metric",
        @Query("appid") apiKey: String
    ): WeatherResponse
}

class WeatherRepository(apiKey: String) {
    private val service: WeatherService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        service = retrofit.create(WeatherService::class.java)
        this.apiKey = apiKey
    }

    private val apiKey: String

    suspend fun fetchWeather(lat: Double, lon: Double): WeatherResponse {
        return service.getCurrentWeather(lat, lon, apiKey = apiKey)
    }
}
