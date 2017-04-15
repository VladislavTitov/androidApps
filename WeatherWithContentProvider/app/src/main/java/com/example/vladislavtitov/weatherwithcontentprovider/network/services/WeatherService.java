package com.example.vladislavtitov.weatherwithcontentprovider.network.services;

import com.example.vladislavtitov.weatherwithcontentprovider.network.models.CityForecast;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherService {

    String appId = "f504d1ab2430389753698888dc84a889";

    @GET("/data/2.5/forecast")
    Call<CityForecast> getForecast(@Query("q") String cityName, @Query("appid") String appId);

}
