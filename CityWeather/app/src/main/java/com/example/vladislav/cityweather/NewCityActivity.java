package com.example.vladislav.cityweather;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewCityActivity extends AppCompatActivity {

    EditText cityName;
    Button addCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);

        cityName = (EditText) findViewById(R.id.newcity);
        addCity = (Button) findViewById(R.id.addcity);

        addCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CitiesProvider.getInstance().recordCity(NewCityActivity.this, cityName.getText().toString());
                finish();
            }
        });
    }
}
