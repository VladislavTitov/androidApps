package com.example.vladislav.cityweather;

import android.os.AsyncTask;
import android.widget.TextView;

import com.example.vladislav.cityweather.pojo.MyWeather;

import java.io.IOException;

import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitAsyncTask extends AsyncTask<String, Void, String> {

    TextView temp;

    public RetrofitAsyncTask(TextView temp) {
        super();
        this.temp = temp;
    }

    @Override
    protected String doInBackground(String... params) {

        String temp = null;

        try {
            String cityName = params[0];

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://api.openweathermap.org")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            WeatherService service = retrofit.create(WeatherService.class);

            Call<MyWeather> myWeatherCall = service.myWeather(cityName, WeatherService.appid);

            /*Request request = myWeatherCall.request();*/

            Response<MyWeather> response = myWeatherCall.execute();

            if (response.isSuccessful()) {
                MyWeather myWeather = response.body();
                temp = String.valueOf(myWeather.getMain().getTemp());
            }else {
                temp = "Error: " + response.code() + "\n" + response.errorBody();
            }


        }catch (IOException e){
            e.printStackTrace();
        }




        return temp;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        temp.setText(s);
    }
}
