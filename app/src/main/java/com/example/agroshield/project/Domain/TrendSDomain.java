package com.example.agroshield.project.Domain;

public class TrendSDomain {

    private String title;
    private String subtitle;
    private String picAddress;
    private String url; // Add a field for the URL

    public TrendSDomain(String title, String subtitle, String picAddress, String url) {
        this.title = title;
        this.subtitle = subtitle;
        this.picAddress = picAddress;
        this.url = url; // Initialize the URL
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getPicAddress() {
        return picAddress;
    }

    public void setPicAddress(String picAddress) {
        this.picAddress = picAddress;
    }

    public String getUrl() { // Add getter for URL
        return url;
    }

    public void setUrl(String url) { // Add setter for URL
        this.url = url;
    }
}
