package com.example.vladislav.cityweather;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.vladislav.cityweather.services.WeatherIntentService;
import com.example.vladislav.cityweather.services.WeatherIntentServiceResult;
import com.example.vladislav.cityweather.services.WeatherReceiver;

public class WeatherActivity extends AppCompatActivity implements AsycCallback, WeatherIntentServiceResult{

    TextView cityName;
    TextView temp;

    WeatherReceiver receiver;

    public static final String ACTION_NAME_FOR_SERVICE = "com.example.vladislav.Service";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        cityName = (TextView) findViewById(R.id.city_name_weather);
        temp = (TextView) findViewById(R.id.temp);

        cityName.setText(getIntent().getStringExtra("city"));

        Intent intent = new Intent(this, WeatherIntentService.class);
        intent.putExtra("name", getIntent().getStringExtra("city"));
        startService(intent);

        //getWeatherFragment();
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

    @Override
    protected void onResume() {
        super.onResume();
        receiver = new WeatherReceiver(this);
        IntentFilter filter = new IntentFilter(ACTION_NAME_FOR_SERVICE);
        registerReceiver(receiver, filter);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getStringExtra("temp") != null){
            temp.setText(intent.getStringExtra("temp"));
        }
    }
}
