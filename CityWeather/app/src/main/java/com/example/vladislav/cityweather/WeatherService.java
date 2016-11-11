package com.example.vladislav.cityweather;

import com.example.vladislav.cityweather.pojo.MyWeather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherService {

    String appid = "f504d1ab2430389753698888dc84a889";

    @GET("/data/2.5/weather")
    Call<MyWeather> myWeather(@Query("q") String cityName, @Query("appid") String appid);
}
