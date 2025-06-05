package com.example.weatherwise

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.tasks.await

class MainActivity : ComponentActivity() {

    private val locationPermissionRequest =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        locationPermissionRequest.launch(Manifest.permission.ACCESS_FINE_LOCATION)

        setContent {
            val vm: WeatherViewModel = viewModel {
                WeatherViewModel(WeatherRepository("YOUR_API_KEY"))
            }
            WeatherScreen(vm)
        }
    }
}

@Composable
fun WeatherScreen(viewModel: WeatherViewModel) {
    val context = LocalContext.current
    val fused = remember { LocationServices.getFusedLocationProviderClient(context) }
    val weather by viewModel.weather.collectAsState()

    LaunchedEffect(Unit) {
        try {
            val location = fused.lastLocation.await()
            if (location != null) {
                viewModel.loadWeather(location.latitude, location.longitude)
            }
        } catch (e: SecurityException) {
            // permission not granted
        }
    }

    Surface(color = MaterialTheme.colors.background) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (weather == null) {
                Text("Loading...")
            } else {
                Text(text = weather!!.city)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "${weather!!.main.temp}°C")
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = weather!!.weather.firstOrNull()?.description ?: "")
            }
        }
    }
}
