package com.example.vladislavtitov.weatherwithcontentprovider;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v7.app.AppCompatActivity;

import com.example.vladislavtitov.weatherwithcontentprovider.app.MyApplication;
import com.example.vladislavtitov.weatherwithcontentprovider.network.models.City;
import com.example.vladislavtitov.weatherwithcontentprovider.network.models.CityForecast;
import com.example.vladislavtitov.weatherwithcontentprovider.network.services.WeatherService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

public class WeatherAsyncLoader extends AsyncTaskLoader<List<CityForecast>>{

    private List<String> cities;

    public WeatherAsyncLoader(Context context, List<String> cities) {
        super(context);
        this.cities = cities;
    }

    @Override
    public List<CityForecast> loadInBackground() {

        List<CityForecast> forecasts = new ArrayList<>();

        MyApplication application = (MyApplication) getContext();

        WeatherService service = application.getSomeComponent().weatherService();

        for (String city : cities) {
            try {
                Response<CityForecast> response = service.getForecast(city, WeatherService.appId).execute();
                if (response.isSuccessful()){
                    forecasts.add(response.body());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        return forecasts;
    }
}
