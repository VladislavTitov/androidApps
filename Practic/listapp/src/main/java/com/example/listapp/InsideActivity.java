package com.example.listapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class InsideActivity extends AppCompatActivity {

    ImageView iv_inside;
    TextView tv_name_inside;
    TextView tv_info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inside);

        //iv_inside = (ImageView) findViewById(R.id.iv_inside);
        tv_name_inside = (TextView) findViewById(R.id.tv_name_inside);
        tv_info = (TextView) findViewById(R.id.tv_info);

        Intent intent = getIntent();

        tv_name_inside.setText(intent.getStringExtra("name"));
        tv_info.setText(intent.getStringExtra("info"));
    }
}
