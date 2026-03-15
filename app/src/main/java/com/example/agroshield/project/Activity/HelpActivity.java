package com.example.agroshield.project.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.agroshield.R;

public class HelpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        // Set up the back arrow button to navigate back to the main screen
        ConstraintLayout messageArrowBtn = findViewById(R.id.messagearrowbtn_3);
        messageArrowBtn.setOnClickListener(v -> navigateToProfile_Activity());

    }

    private void navigateToProfile_Activity() {
        Intent intent = new Intent(HelpActivity.this, profileActivity.class);
        startActivity(intent);
    }


}
