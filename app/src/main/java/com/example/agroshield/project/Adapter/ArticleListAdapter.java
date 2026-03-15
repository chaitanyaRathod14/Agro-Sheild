package com.example.agroshield.project.Adapter;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.agroshield.R;
import com.example.agroshield.project.Domain.ActivityDomain;

import java.util.ArrayList;

public class ArticleListAdapter extends RecyclerView.Adapter<ArticleListAdapter.ArticleViewHolder> {

    private ArrayList<ActivityDomain> articleList;

    // Constructor to initialize the list of articles
    public ArticleListAdapter(ArrayList<ActivityDomain> articleList) {
        this.articleList = articleList;
    }

    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the item view for the RecyclerView
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.viewholder_trend_list_2, parent, false);
        return new ArticleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder holder, int position) {
        // Get the item (Article) at the current position
        ActivityDomain item = articleList.get(position);

        // Set the title and subtitle text views with the data from the ActivityDomain
        holder.titleTextView.setText(item.getTitle());
        holder.subtitleTextView.setText(item.getSubtitle());

        // Load the image into the ImageView using Glide
        String imageName = item.getImageName(); // Retrieve image name or use placeholder
        int imageResource = holder.itemView.getContext().getResources()
                .getIdentifier(imageName, "drawable", holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext())
                .load(imageResource)  // Load image from drawable resource
                .placeholder(R.drawable.article_placeholder)  // Optional placeholder image
                .into(holder.imageView);

        // Handle article URL click (navigate to the URL in the browser)
        holder.itemView.setOnClickListener(v -> {
            String articleUrl = item.getUrl();  // Get the URL from ActivityDomain
            if (articleUrl != null && !articleUrl.isEmpty()) {
                // Create an intent to open the URL in the browser
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(articleUrl));
                holder.itemView.getContext().startActivity(browserIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return articleList.size();  // Return the number of articles in the list
    }

    // ViewHolder to hold the references to the views in each list item
    static class ArticleViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        TextView subtitleTextView;
        ImageView imageView;  // ImageView for article image

        public ArticleViewHolder(@NonNull View itemView) {
            super(itemView);
            // Bind the views to the ViewHolder
            titleTextView = itemView.findViewById(R.id.article_title);
            subtitleTextView = itemView.findViewById(R.id.article_subtitle);
            imageView = itemView.findViewById(R.id.article_image);  // ImageView for the article's image
        }
    }
}
