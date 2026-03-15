package com.example.agroshield.project.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.example.agroshield.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MessageActivity extends AppCompatActivity {

    private LinearLayout messageContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        messageContainer = findViewById(R.id.messageContainer);

        // Set up the home button navigation
        ConstraintLayout messagearrowbtn = findViewById(R.id.messagearrowbtn);
        messagearrowbtn.setOnClickListener(v -> navigateToHome());

        // Set up the profile button navigation
        ImageView profileButton = findViewById(R.id.Profile_btn);
        profileButton.setOnClickListener(v -> navigateToProfile());

        // Read and display specific SMS messages
        readSpecificSmsMessage();
    }


    private void navigateToHome() {
        Intent intent = new Intent(MessageActivity.this, MainActivity.class);
        startActivity(intent);
    }

    private void navigateToProfile() {
        Intent intent = new Intent(MessageActivity.this, profileActivity.class);
        startActivity(intent);
    }

    private void readSpecificSmsMessage() {
        try {
            Uri uri = Uri.parse("content://sms/inbox");
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);

            if (cursor != null && cursor.moveToFirst()) {
                do {
                    int addressColumnIndex = cursor.getColumnIndexOrThrow("address");
                    int bodyColumnIndex = cursor.getColumnIndexOrThrow("body");
                    int dateColumnIndex = cursor.getColumnIndexOrThrow("date");

                    String address = cursor.getString(addressColumnIndex);
                    String body = cursor.getString(bodyColumnIndex);
                    long timestamp = cursor.getLong(dateColumnIndex);

                    String normalizedAddress = address.replace(" ", "").replace("-", "").replace("+91", "").replaceFirst("^0", "");

                    if (normalizedAddress.equals("8956329620")) {
                        if (body.contains("An intruder detected in zone") && body.contains("with temperature")) {
                            String formattedTime = formatTimestamp(timestamp);

                            // Extract and save temperature for future use
                            float temperature = extractTemperature(body);
                            if (temperature != -1f) {
                                saveTemperatureForManualView(temperature);
                            }

                            addMessageToLayout(body, formattedTime);
                            return;
                        }
                    }
                } while (cursor.moveToNext());

                cursor.close();
            } else {
                addMessageToLayout("No relevant SMS found.", "");
            }
        } catch (Exception e) {
            e.printStackTrace();
            addMessageToLayout("Error reading SMS.", "");
        }
    }

    private String formatTimestamp(long timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.getDefault());
        return sdf.format(new Date(timestamp));
    }

    private void addMessageToLayout(String message, String timestamp) {
        runOnUiThread(() -> {
            @SuppressLint("InflateParams") View messageView = LayoutInflater.from(this).inflate(R.layout.message_item, null);

            TextView messageTextView = messageView.findViewById(R.id.messageText);
            TextView timestampTextView = messageView.findViewById(R.id.messageTime);

            messageTextView.setText(message);
            timestampTextView.setText(timestamp);

            Button checkEntityButton = messageView.findViewById(R.id.checkEntityButton);
            checkEntityButton.setOnClickListener(v -> {
                // Extract data before navigating to the EntityClassifier
                float temperature = extractTemperature(message);
                int zoneNumber = extractZoneNumber(message);
                if (temperature != -1 && zoneNumber != -1) {
                    navigateToEntityClassifier(temperature, zoneNumber);
                }
            });

            messageContainer.addView(messageView);

            // Highlight the relevant zone in ZoneDetectionActivity
            int zoneNumber = extractZoneNumber(message);
            if (zoneNumber != -1) {
                navigateToZoneDetection(zoneNumber);
            }
        });
    }

    private void navigateToEntityClassifier(float temperature, int zoneNumber) {
        Intent intent = new Intent(MessageActivity.this, EntityClassifier.class);
        intent.putExtra("temperature", temperature);
        intent.putExtra("zoneNumber", zoneNumber);  // Pass the zone number
        startActivity(intent);
    }

    private void navigateToZoneDetection(int zoneNumber) {
        Intent intent = new Intent(MessageActivity.this, ZoneDetectionActivity.class);
        intent.putExtra("zoneNumber", zoneNumber);  // Pass the selected zone number
        startActivity(intent);
    }

    private void saveTemperatureForManualView(float temperature) {
        SharedPreferences preferences = getSharedPreferences("AgroShieldPrefs", MODE_PRIVATE);
        preferences.edit().putFloat("detectedTemperature", temperature).apply();
    }

    private int extractZoneNumber(String body) {
        // Example: Extract zone from "zone X"
        String zonePattern = "(?<=zone\\s)(\\d+)";
        try {
            java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(zonePattern);
            java.util.regex.Matcher matcher = pattern.matcher(body);
            if (matcher.find()) {
                return Integer.parseInt(matcher.group(1));
            }
        } catch (NumberFormatException e) {
            Log.e("MessageActivity", "Failed to extract zone number from: " + body);
        }
        return -1; // Return -1 if no valid zone is found
    }

    private float extractTemperature(String body) {
        // Example: Extract temperature from "temperature: Y"
        String temperaturePattern = "(?<=temperature:\\s)(\\d+\\.\\d+)";
        try {
            java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(temperaturePattern);
            java.util.regex.Matcher matcher = pattern.matcher(body);
            if (matcher.find()) {
                return Float.parseFloat(matcher.group(1));
            }
        } catch (NumberFormatException e) {
            Log.e("MessageActivity", "Failed to extract temperature from: " + body);
        }
        return -1f; // Return -1 if no valid temperature is found
    }
}