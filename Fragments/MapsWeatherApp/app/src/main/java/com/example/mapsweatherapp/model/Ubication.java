package com.example.mapsweatherapp.model;

public class Ubication {
    String name;
    double latitude;
    double longitude;

    public Ubication(String name, double latitude, double longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
