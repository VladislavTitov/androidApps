package com.example.vladislav.cityweather.services;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.example.vladislav.cityweather.WeatherActivity;
import com.example.vladislav.cityweather.WeatherService;
import com.example.vladislav.cityweather.pojo.MyWeather;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherIntentService extends IntentService {

    public WeatherIntentService() {
        super("WeatherIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        Log.d("mylogs", "onHandleIntent");

        String temp = "meepmeep";
        try {


            String cityName = intent.getStringExtra("name");

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://api.openweathermap.org")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            WeatherService service = retrofit.create(WeatherService.class);

            Call<MyWeather> myWeatherCall = service.myWeather(cityName, WeatherService.appid);

            Response<MyWeather> response = myWeatherCall.execute();

            if (response.isSuccessful()) {
                MyWeather myWeather = response.body();
                temp = String.valueOf(myWeather.getMain().getTemp() - 273.15);
            }else {
                temp = "Error: " + response.code();
            }


        }catch (IOException e){
            e.printStackTrace();
        }

        Intent intent1 = new Intent();
        intent1.setAction(WeatherActivity.ACTION_NAME_FOR_SERVICE);
        intent1.putExtra("temp", temp);
        sendBroadcast(intent1);

    }
}
