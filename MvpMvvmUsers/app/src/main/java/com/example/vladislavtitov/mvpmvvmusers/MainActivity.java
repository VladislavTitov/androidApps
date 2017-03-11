package com.example.vladislavtitov.mvpmvvmusers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.vladislavtitov.mvpmvvmusers.mvp.MvpActivity;
import com.example.vladislavtitov.mvpmvvmusers.mvvm.MvvmActivity;

public class MainActivity extends AppCompatActivity {

    Button btnToMvp;
    Button btnToMvvm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnToMvp = (Button) findViewById(R.id.btn_to_mvp);
        btnToMvvm = (Button) findViewById(R.id.btn_to_mvvm);

        btnToMvp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMvp = new Intent(MainActivity.this, MvpActivity.class);
                startActivity(intentMvp);
            }
        });

        btnToMvvm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMvvm = new Intent(MainActivity.this, MvvmActivity.class);
                startActivity(intentMvvm);
            }
        });

    }
}
