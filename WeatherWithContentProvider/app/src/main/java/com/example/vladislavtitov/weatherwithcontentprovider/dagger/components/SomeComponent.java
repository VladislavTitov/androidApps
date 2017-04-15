package com.example.vladislavtitov.weatherwithcontentprovider.dagger.components;

import com.example.vladislavtitov.weatherwithcontentprovider.dagger.modules.WeatherServiceModule;
import com.example.vladislavtitov.weatherwithcontentprovider.network.services.WeatherService;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {WeatherServiceModule.class})
public interface SomeComponent {

    WeatherService weatherService();

}
