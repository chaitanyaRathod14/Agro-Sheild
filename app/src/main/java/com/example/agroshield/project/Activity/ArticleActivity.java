package com.example.agroshield.project.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agroshield.R;
import com.example.agroshield.project.Adapter.ArticleListAdapter;
import com.example.agroshield.project.Domain.ActivityDomain;
import java.util.ArrayList;

public class ArticleActivity extends AppCompatActivity {

    private RecyclerView recyclerViewArticles;
    private ArticleListAdapter adapterArticles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);

        // Initialize the RecyclerView
        initRecyclerView();
    }

    private void initRecyclerView() {
        // Create a list of articles
        ArrayList<ActivityDomain> items = new ArrayList<>();
        items.add(new ActivityDomain(
                "Enable agriculture to be.. future-ready",
                "The contradiction is clear. While in my understanding. The road to achieving the prime minister...",
                "article5",  // Image name in drawable (e.g., article5.png)
                "https://www.tribuneindia.com/news/comment/enable-agriculture-to-be-future-ready-366783/"
        ));

        items.add(new ActivityDomain(
                "Advancement of kisan.. drones",
                "The Prime Minister, Shri Narendra Modi, has reiterated how the advancement of drones in agriculture...",
                "article4",  // Image name in drawable (e.g., article4.png)
                "https://www.pmindia.gov.in/en/news_updates/advancement-of-kisan-drones-provides-an-effective-and-efficient-technique-in-agriculture-sector-pm"
        ));

        items.add(new ActivityDomain(
                "The Miracle in Agriculture.",
                "The miracle of agriculture is one of humanity's most outstanding achievements. The ability to grow food...",
                "article2",  // Image name in drawable (e.g., article2.png)
                "https://www.agrirs.co.uk/blog/2022/07/the-miracle-of-agriculture?source=google.com"
        ));

        // Set up the RecyclerView
        recyclerViewArticles = findViewById(R.id.recyclerViewArticles);
        recyclerViewArticles.setLayoutManager(new LinearLayoutManager(this));

        // Set the adapter to the RecyclerView
        adapterArticles = new ArticleListAdapter(items);
        recyclerViewArticles.setAdapter(adapterArticles);

        // Set up the home button navigation
        ConstraintLayout messagearrowbtn = findViewById(R.id.messagearrowbtn_3);
        messagearrowbtn.setOnClickListener(v -> navigateToMainActivity());


    }

    private void navigateToMainActivity() {
        Intent intent = new Intent(ArticleActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
