# WeatherWise

WeatherWise is a simple Android application built with Kotlin and Jetpack Compose that displays the current weather for your location. It uses the [OpenWeather API](https://openweathermap.org/api) to fetch real-time data.

## Features

- Obtains your GPS position and displays current temperature and description.
- Built with Jetpack Compose for a responsive UI.
- Uses Retrofit for networking and Google Play Services for location.

## Setup

1. Obtain an API key from OpenWeather.
2. Replace `YOUR_API_KEY` in `MainActivity.kt` with your API key.
3. Create a `gradle.properties` file to enable AndroidX support:

   ```properties
   android.useAndroidX=true
   android.enableJetifier=true
   ```

   The project uses the Material Components library for its `Material3` theme, which Gradle will download automatically.

4. Generate the Gradle wrapper JAR (only required once) by running:

   ```bash
   gradle wrapper
   ```

5. Build the project with the wrapper:

   ```bash
   ./gradlew assembleDebug
   ```

6. Ensure Gradle can locate your Android SDK. Either set the `ANDROID_HOME` environment variable or create a `local.properties` file at the project root with:

   ```properties
   sdk.dir=/path/to/Android/sdk
   ```

An APK will be generated in `app/build/outputs/apk/debug/`.

## Note

This project is a minimal example and does not include error handling or permission rationale dialogs. It is intended as a starting point for further development.
