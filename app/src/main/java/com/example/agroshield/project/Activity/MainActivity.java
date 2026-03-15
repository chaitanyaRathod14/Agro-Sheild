package com.example.agroshield.project.Activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agroshield.R;
import com.example.agroshield.project.Adapter.TrendsAdapter;
import com.example.agroshield.project.Domain.TrendSDomain;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_SMS_PERMISSION = 100;
    private RecyclerView.Adapter adapterTrendsList;
    private RecyclerView recyclerViewTrends;
    private LinearLayout zoneDetectionBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout viewAllBtn = findViewById(R.id.View_all_btn);
        viewAllBtn.setOnClickListener(v -> navigateToArticleActivity());

        ImageView profileButton = findViewById(R.id.prof);
        profileButton.setOnClickListener(v -> navigateToProfile());

        LinearLayout weatherCardView = findViewById(R.id.Weathercardview);
        weatherCardView.setOnClickListener(v -> navigateToWeather());

        CardView zoneCardView = findViewById(R.id.ZoneCardView_1);
        zoneCardView.setOnClickListener(v -> navigateToZone());

        ImageView cloudIcon = findViewById(R.id.CLoud_icon);
        cloudIcon.setOnClickListener(v -> navigateToWeather_icon());

        initRecyclerView();
        setupBottomNavigation();
        setupReadSmsButton();

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_SMS}, REQUEST_SMS_PERMISSION);
        }

        zoneDetectionBtn = findViewById(R.id.zoneDetectionBtn);
        zoneDetectionBtn.setOnClickListener(view -> navigateToZone());
    }

    private void navigateToArticleActivity() {
        Intent intent = new Intent(MainActivity.this, ArticleActivity.class);
        startActivity(intent);
    }

    private void navigateToWeather_icon() {
        Intent intent = new Intent(MainActivity.this, WeatherActivity.class);
        startActivity(intent);
    }

    private void navigateToZone() {
        Intent intent = new Intent(MainActivity.this, ZoneDetectionActivity.class);
        startActivity(intent);
    }

    private void navigateToWeather() {
        Intent intent = new Intent(MainActivity.this, WeatherActivity.class);
        startActivity(intent);
    }

    private void navigateToProfile() {
        Intent intent = new Intent(MainActivity.this, profileActivity.class);
        startActivity(intent);
    }

    private void setupBottomNavigation() {
        LinearLayout profileBtn = findViewById(R.id.profileBtn);
        profileBtn.setOnClickListener(view -> navigateToProfile());
    }

    private void initRecyclerView() {
        ArrayList<TrendSDomain> items = new ArrayList<>();
        items.add(new TrendSDomain("Enable agriculture to be future-ready",
                "The contradiction is clear, While in my understand...",
                "article5",
                "https://www.tribuneindia.com/news/comment/enable-agriculture-to-be-future-ready-366783/"));
        items.add(new TrendSDomain("Advancement of kisan drones",
                "The Prime Minister, Shri Narendra Modi, has retei...",
                "article4",
                "https://www.pmindia.gov.in/en/news_updates/advancement-of-kisan-drones-provides-an-effective-and-efficient-technique-in-agriculture-sector-pm"));
        items.add(new TrendSDomain("The Miracle in Agriculture",
                "The miracle of agriculture is one of humanity's most...",
                "article2",
                "https://www.agrirs.co.uk/blog/2022/07/the-miracle-of-agriculture?source=google.com"));

        recyclerViewTrends = findViewById(R.id.view1);
        recyclerViewTrends.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        adapterTrendsList = new TrendsAdapter(items);
        recyclerViewTrends.setAdapter(adapterTrendsList);
    }

    private void setupReadSmsButton() {
        LinearLayout readSmsButton = findViewById(R.id.messageBtn);

        readSmsButton.setOnClickListener(view -> {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(MainActivity.this, MessageActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Please grant SMS permission", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_SMS_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Permission denied to read SMS", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
