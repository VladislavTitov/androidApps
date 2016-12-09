package com.example.vladislav.cityweather;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.example.vladislav.cityweather.pojo.MyWeather;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherFragment extends Fragment {

    private AsycCallback asycCallback;
    private RetrofitAsyncTask asyncTask;
    private String cityName;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        attachCallback(context);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        attachCallback(activity);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        this.cityName = getArguments().getString("city");
        Log.d(RetrofitAsyncTask.MY_TAG, "onCreate: this is city arguments: " + getArguments().getString("city"));
        startTask();
    }

    private void attachCallback(Context context){
        if (context instanceof AsycCallback){
            asycCallback = (AsycCallback) context;
        }else {
            throw new IllegalStateException("You aren't implementing AsyncCallback in activity");
        }
    }

    public void startTask(){
        if (asyncTask == null){
            asyncTask = new RetrofitAsyncTask();
            Log.d(RetrofitAsyncTask.MY_TAG, "startTask: this is cityname: " + cityName);
            asyncTask.execute(cityName);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        asycCallback = null;
    }


    public class RetrofitAsyncTask extends AsyncTask<String, Void, String> {

        public static final String MY_TAG = "mylogs";

        @Override
        protected String doInBackground(String... params) {

            String temp = null;

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                String cityName = params[0];

                for (String str :
                        params) {
                    Log.d(MY_TAG, "doInBackground: this is params: " + str);
                }

                Log.d(MY_TAG, "doInBackground: this is city name " + cityName);

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

            Log.d(MY_TAG, "doInBackground: this is temp " + temp);


            return temp;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            asycCallback.getTemp(s);
        }
    }

}
