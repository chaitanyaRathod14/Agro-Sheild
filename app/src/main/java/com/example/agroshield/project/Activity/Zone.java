package com.example.agroshield.project.Activity;

public class Zone {

    private String name;
    private int color;

    public Zone(String name, int color) {
        this.name = name;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
