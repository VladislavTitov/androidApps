package com.example.vladislavtitov.weatherwithcontentprovider.db.models;

public class WeatherCity {

    private String name;

    public WeatherCity() {
    }

    public WeatherCity(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
