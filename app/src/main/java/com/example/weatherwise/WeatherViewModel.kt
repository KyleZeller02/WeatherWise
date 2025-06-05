package com.example.weatherwise

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherwise.data.WeatherResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WeatherViewModel(private val repository: WeatherRepository) : ViewModel() {
    private val _weather = MutableStateFlow<WeatherResponse?>(null)
    val weather: StateFlow<WeatherResponse?> = _weather

    fun loadWeather(lat: Double, lon: Double) {
        viewModelScope.launch {
            try {
                _weather.value = repository.fetchWeather(lat, lon)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
