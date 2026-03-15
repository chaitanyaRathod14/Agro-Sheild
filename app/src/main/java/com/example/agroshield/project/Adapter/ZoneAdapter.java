package com.example.agroshield.project.Adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.agroshield.R;
import com.example.agroshield.project.Activity.Zone;
import java.util.List;

public class ZoneAdapter extends RecyclerView.Adapter<ZoneAdapter.ZoneViewHolder> {

    private final List<Zone> zones;

    public ZoneAdapter(List<Zone> zones) {
        this.zones = zones;
    }

    @NonNull
    @Override
    public ZoneViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.zone_item, parent, false);
        return new ZoneViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ZoneViewHolder holder, int position) {
        Zone zone = zones.get(position);
        holder.zoneTextView.setText(zone.getName());
        holder.zoneTextView.setBackgroundColor(zone.getColor());
    }

    @Override
    public int getItemCount() {
        return zones.size();
    }

    static class ZoneViewHolder extends RecyclerView.ViewHolder {
        TextView zoneTextView;

        public ZoneViewHolder(@NonNull View itemView) {
            super(itemView);
            zoneTextView = itemView.findViewById(R.id.zoneTextView);
        }
    }
}
