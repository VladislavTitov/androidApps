package com.example.vladislavtitov.weatherwithcontentprovider.app;

import android.app.Application;

import com.example.vladislavtitov.weatherwithcontentprovider.dagger.components.DaggerSomeComponent;
import com.example.vladislavtitov.weatherwithcontentprovider.dagger.components.SomeComponent;
import com.example.vladislavtitov.weatherwithcontentprovider.dagger.modules.WeatherServiceModule;


public class MyApplication extends Application {

    private SomeComponent someComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        someComponent = DaggerSomeComponent.builder()
                .weatherServiceModule(new WeatherServiceModule())
                .build();
    }

    public SomeComponent getSomeComponent() {
        return someComponent;
    }
}
