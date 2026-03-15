package com.example.agroshield.project.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.agroshield.R;

public class TermsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms);

        // Set up the button to navigate to MainActivity
        Button acceptButton = findViewById(R.id.acceptButton);
        acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToMainActivity();
            }
        });

        // Set up the back arrow button navigation to ProfileActivity
        ConstraintLayout messageArrowBtn = findViewById(R.id.messagearrowbtn_3);
        messageArrowBtn.setOnClickListener(v -> navigateToProfileActivity());
    }

    private void navigateToProfileActivity() {
        Intent intent = new Intent(TermsActivity.this, profileActivity.class);
        startActivity(intent);
    }

    private void navigateToMainActivity() {
        Intent intent = new Intent(TermsActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
