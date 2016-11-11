package com.example.vladislav.cityweather;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class WeatherActivity extends AppCompatActivity {

    TextView cityName;
    TextView temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        cityName = (TextView) findViewById(R.id.city_name_weather);
        temp = (TextView) findViewById(R.id.temp);

        cityName.setText(getIntent().getStringExtra("city"));
        RetrofitAsyncTask asyncTask = new RetrofitAsyncTask(temp);
        asyncTask.execute(getIntent().getStringExtra("city"));
    }
}
