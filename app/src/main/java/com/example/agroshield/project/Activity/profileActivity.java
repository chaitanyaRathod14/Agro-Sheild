package com.example.agroshield.project.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.agroshield.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class profileActivity extends AppCompatActivity {

    FirebaseAuth auth;
    Button button;
    TextView textview;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile);

        ConstraintLayout ConstraintLayout100 = findViewById(R.id.Notification_bar_1);
        ConstraintLayout100 .setOnClickListener(v -> navigateToMessagepg());

        ConstraintLayout ConstraintLayout200 = findViewById(R.id.Notification_bar_4);
        ConstraintLayout200 .setOnClickListener(v -> navigateToTermsActivity());

        ConstraintLayout ConstraintLayout300 = findViewById(R.id.Notification_bar_5

        );
        ConstraintLayout300 .setOnClickListener(v -> navigateToHelpActivity());

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        button = findViewById(R.id.logout);
        textview = findViewById(R.id.user_details);
        ConstraintLayout constraintLayout = findViewById(R.id.constraintLayout2);

        if (user == null) {
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
            finish();
        } else {
            textview.setText(user.getEmail());
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });

        constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(profileActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void navigateToHelpActivity() {
        Intent intent = new Intent(profileActivity.this, HelpActivity.class);
        startActivity(intent);
    }

    private void navigateToMessagepg() {
        Intent intent = new Intent(profileActivity.this, MessageActivity.class);
        startActivity(intent);
    }

    private void navigateToTermsActivity() {
        Intent intent = new Intent(profileActivity.this, TermsActivity.class);
        startActivity(intent);
    }

}
