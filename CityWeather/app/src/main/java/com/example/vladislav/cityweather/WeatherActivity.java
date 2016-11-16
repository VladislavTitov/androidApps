package com.example.vladislav.cityweather;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class WeatherActivity extends AppCompatActivity implements AsycCallback{

    TextView cityName;
    TextView temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        cityName = (TextView) findViewById(R.id.city_name_weather);
        temp = (TextView) findViewById(R.id.temp);

        cityName.setText(getIntent().getStringExtra("city"));

        getWeatherFragment();
    }

    public WeatherFragment getWeatherFragment(){
        FragmentManager manager = getSupportFragmentManager();
        WeatherFragment fragment = (WeatherFragment) manager.findFragmentByTag(WeatherFragment.class.getName());

        if (fragment == null){
            fragment = new WeatherFragment();

            Bundle bundle = new Bundle();
            bundle.putString("city", getIntent().getStringExtra("city"));

            Log.d(WeatherFragment.RetrofitAsyncTask.MY_TAG, "getWeatherFragment: this is city: " + getIntent().getStringExtra("city"));

            fragment.setArguments(bundle);

            manager.beginTransaction()
                    .add(fragment, WeatherFragment.class.getName())
                    .commit();
        }
        return fragment;

    }

    @Override
    public void getTemp(String temp) {
        this.temp.setText(temp);
    }
}
