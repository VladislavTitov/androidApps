package com.example.intenttesting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    TextView tv1;
    TextView tv2;
    TextView tv_counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        tv1 = (TextView) findViewById(R.id.tv_1);
        tv2 = (TextView) findViewById(R.id.tv_2);
        tv_counter = (TextView) findViewById(R.id.tv_counter);

        Intent intent = getIntent();

        tv1.setText(intent.getStringExtra("text"));
        tv2.setText(intent.getStringExtra("number"));
        tv_counter.setText(intent.getStringExtra("count"));

    }
}
