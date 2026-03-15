package com.example.agroshield.project.Domain;

public class ActivityDomain {
    private String title;
    private String subtitle;
    private String imageName;  // Name of the image resource
    private String url;  // URL to navigate to when clicked

    // Constructor
    public ActivityDomain(String title, String subtitle, String imageName, String url) {
        this.title = title;
        this.subtitle = subtitle;
        this.imageName = imageName;
        this.url = url;
    }

    // Getters
    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public String getImageName() {
        return imageName;
    }

    public String getUrl() {
        return url;
    }
}
