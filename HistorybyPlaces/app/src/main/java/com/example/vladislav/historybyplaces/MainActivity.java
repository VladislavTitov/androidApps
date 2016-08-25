package com.example.vladislav.historybyplaces;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tv_play;
    TextView tv_records;
    TextView tv_options;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_play = (TextView) findViewById(R.id.start);
        tv_records = (TextView) findViewById(R.id.tv_records);
        tv_options = (TextView) findViewById(R.id.tv_options);

        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/CormorantInfant-Regular.ttf");
        tv_play.setTypeface(tf);
        tv_records.setTypeface(tf);
        tv_options.setTypeface(tf);

        tv_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, GameActivity.class);
                startActivity(intent);
            }
        });
    }
}
