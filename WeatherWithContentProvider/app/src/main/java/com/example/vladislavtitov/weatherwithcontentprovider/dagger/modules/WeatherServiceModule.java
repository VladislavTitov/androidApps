package com.example.vladislavtitov.weatherwithcontentprovider.dagger.modules;

import com.example.vladislavtitov.weatherwithcontentprovider.network.services.WeatherService;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class WeatherServiceModule {

    private WeatherService weatherService;

    public WeatherServiceModule() {
        weatherService = buildWeatherService();
    }

    private WeatherService buildWeatherService(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(WeatherService.class);

    }

    @Provides
    public WeatherService getWeatherService() {
        return weatherService;
    }
}
