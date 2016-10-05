package com.therishka.androidlab_2;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ItemActivity extends AppCompatActivity {

    LinearLayout ll;
    TextView tv_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        ll = (LinearLayout) findViewById(R.id.activity_item);
        tv_name = (TextView) findViewById(R.id.tv_name);

        tv_name.setText(getIntent().getStringExtra("name"));

        String color = getIntent().getStringExtra("color");
        if (color.equals("black")){
            ll.setBackgroundColor(Color.parseColor("#123456"));
        }else if (color.equals("white")){
            ll.setBackgroundColor(Color.parseColor("#654321"));
        }
    }
}
