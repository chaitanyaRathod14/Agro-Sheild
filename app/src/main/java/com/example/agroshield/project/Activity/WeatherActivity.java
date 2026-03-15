package com.example.agroshield.project.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.agroshield.R;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WeatherActivity extends AppCompatActivity {

    private static final String API_KEY = "014c815db7d13da219ab1951c46b9f03"; // API key
    private static final String WEATHER_API_URL = "https://api.openweathermap.org/data/2.5/weather";

    private EditText cityNameEditText;
    private Button getWeatherButton;
    private TextView cityNameDisplayTextView, weatherInfoTextView, temperatureTextView, dateTimeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whether);

        // Set up the home button navigation
        ConstraintLayout WeatherBackArrow = findViewById(R.id.WeatherBackArrow);
        WeatherBackArrow.setOnClickListener(v -> navigatesToHome());

        cityNameEditText = findViewById(R.id.cityNameInput);
        getWeatherButton = findViewById(R.id.getWeatherButton);
        cityNameDisplayTextView = findViewById(R.id.cityNameDisplay);
        weatherInfoTextView = findViewById(R.id.weatherText);
        temperatureTextView = findViewById(R.id.temperature);
        dateTimeTextView = findViewById(R.id.currentDateTime);

        getWeatherButton.setOnClickListener(v -> {
            String cityName = cityNameEditText.getText().toString();
            if (!cityName.isEmpty()) {
                fetchWeatherData(cityName);
            } else {
                weatherInfoTextView.setText("Please enter a city name");
            }
        });
    }

    private void navigatesToHome() {
        Intent intent = new Intent(WeatherActivity.this, MainActivity.class);
        startActivity(intent);
    }

    private void fetchWeatherData(String cityName) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            String urlString = WEATHER_API_URL + "?q=" + cityName + "&appid=" + API_KEY + "&units=metric";
            StringBuilder result = new StringBuilder();

            try {
                URL url = new URL(urlString);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }
                reader.close();

                String weatherData = result.toString();
                runOnUiThread(() -> updateUI(weatherData));

            } catch (Exception e) {
                Log.e("WeatherActivity", "Error fetching weather data", e);
                runOnUiThread(() -> weatherInfoTextView.setText("Error retrieving weather data"));
            }
        });
    }

    private void updateUI(String result) {
        if (result == null || result.isEmpty()) {
            weatherInfoTextView.setText("Error retrieving weather data");
            return;
        }

        try {
            JSONObject jsonObject = new JSONObject(result);
            JSONObject main = jsonObject.getJSONObject("main");
            double temp = main.getDouble("temp");
            String weatherDescription = jsonObject.getJSONArray("weather").getJSONObject(0).getString("description");
            String cityName = jsonObject.getString("name"); // Get the city name from the response

            // Get current date and time
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());
            String currentDateTime = dateFormat.format(new Date());

            // Format the text
            String temperatureText = String.format("%.2f°C", temp);
            String weatherText = String.format(" %s", weatherDescription);

            // Update the UI
            cityNameDisplayTextView.setText(cityName);
            dateTimeTextView.setText(currentDateTime);
            temperatureTextView.setText(temperatureText);
            weatherInfoTextView.setText(weatherText);

        } catch (Exception e) {
            weatherInfoTextView.setText("Error parsing weather data");
            Log.e("WeatherActivity", "Error parsing weather data", e);
        }
    }
}
