package com.example.vladislav.cityweather.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class WeatherReceiver extends BroadcastReceiver {

    WeatherIntentServiceResult result;

    public WeatherReceiver(WeatherIntentServiceResult result) {
        this.result = result;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        result.onReceive(context, intent);
    }
}
