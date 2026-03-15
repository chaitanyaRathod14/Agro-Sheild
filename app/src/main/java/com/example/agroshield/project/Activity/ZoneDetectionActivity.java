package com.example.agroshield.project.Activity;

import android.graphics.Color;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.agroshield.R;
import com.example.agroshield.project.Adapter.ZoneAdapter;
import java.util.ArrayList;
import java.util.List;

public class ZoneDetectionActivity extends AppCompatActivity {

    private RecyclerView zoneRecyclerView;
    private ZoneAdapter zoneAdapter;
    private List<Zone> zoneList;

    // This will hold the selected zone to highlight
    private int highlightedZone = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zone_detection);

        zoneRecyclerView = findViewById(R.id.zoneRecyclerView);

        // Initialize zone list with default color (green)
        zoneList = new ArrayList<>();
        for (int i = 1; i <= 4; i++) {
            zoneList.add(new Zone("Zone " + i, Color.GREEN)); // Default color green
        }

        // Set up RecyclerView
        zoneAdapter = new ZoneAdapter(zoneList);
        zoneRecyclerView.setLayoutManager(new GridLayoutManager(this, 2)); // 2 columns
        zoneRecyclerView.setAdapter(zoneAdapter);

        // Receive zone number from Intent and highlight the corresponding zone
        highlightedZone = getIntent().getIntExtra("zoneNumber", -1);
        if (highlightedZone != -1) {
            highlightZone(highlightedZone);
        }
    }

    private void highlightZone(int zoneNumber) {
        for (int i = 0; i < zoneList.size(); i++) {
            // Highlight the zone with RED and others with GREEN
            if (i == zoneNumber - 1) {
                zoneList.get(i).setColor(Color.RED); // Highlight this zone
            } else {
                zoneList.get(i).setColor(Color.GREEN); // Reset others
            }
        }
        zoneAdapter.notifyDataSetChanged(); // Refresh RecyclerView to apply color

    }
}
