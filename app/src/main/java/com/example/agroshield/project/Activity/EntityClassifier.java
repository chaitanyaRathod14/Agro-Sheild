package com.example.agroshield.project.Activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import com.example.agroshield.R;

public class EntityClassifier extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entity_classifier);

        // Retrieve the zone number and temperature passed from MessageActivity
        int zoneNumber = getIntent().getIntExtra("zoneNumber", -1);
        float temperature = getIntent().getFloatExtra("temperature", -1);

        // Log the zone number and temperature values
        Log.d("EntityClassifier", "Zone: " + zoneNumber + " | Temperature: " + temperature);

        if (zoneNumber != -1) {
            classifyEntity(zoneNumber, temperature);
        } else {
            Log.e("EntityClassifier", "No zone number passed");
        }
    }

    private void classifyEntity(int zoneNumber, float temperature) {
        String intruderName = "Unknown";
        String intruderMessage = "An intruder detected in Zone " + zoneNumber;

        // Match temperature to an entity with corrected ranges
        if (temperature >= 37.0 && temperature <= 38.9) {
            intruderName = "Sheep";
        } else if (temperature >= 39.0 && temperature <= 40.0) {
            intruderName = "Cow";
        } else if (temperature > 40.0 && temperature <= 41.0) {
            intruderName = "Pig";
        } else if (temperature >= 36.0 && temperature <= 37.5) {
            intruderName = "Human";
        }

        Log.d("EntityClassifier", "Intruder Name: " + intruderName);
        Log.d("EntityClassifier", "Message: " + intruderMessage);

        displayEntity(intruderName, intruderMessage);
    }

    
    private void displayEntity(String intruderName, String intruderMessage) {
        CardView cardView = findViewById(R.id.cardView);
        ImageView imageView = cardView.findViewById(R.id.imageView);
        TextView nameView = cardView.findViewById(R.id.nameView);
        TextView messageView = cardView.findViewById(R.id.messageView);

        // Update entity image based on intruder type
        switch (intruderName) {
            case "Sheep":
                imageView.setImageResource(R.drawable.sheep_image);
                break;
            case "Cow":
                imageView.setImageResource(R.drawable.cow_image);
                break;
            case "Pig":
                imageView.setImageResource(R.drawable.pig_image);
                break;
            case "Human":
                imageView.setImageResource(R.drawable.human_image);
                break;
            default:
                imageView.setImageResource(R.drawable.ic_placeholder_10);
                break;
        }

        // Update the name and message text views
        nameView.setText(intruderName);
        messageView.setText(intruderMessage);
    }
}
