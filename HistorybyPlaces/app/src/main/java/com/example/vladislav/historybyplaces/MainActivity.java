package com.example.vladislav.historybyplaces;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity{

    TextView tv_play;
    TextView tv_records;
    TextView tv_options;

    static MediaPlayer mp;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_play = (TextView) findViewById(R.id.start);
        tv_records = (TextView) findViewById(R.id.tv_records);
        tv_options = (TextView) findViewById(R.id.tv_options);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        mp = MediaPlayer.create(this, R.raw.ktr);
        mp.setLooping(true);
        mp.setVolume(100, 100);
        mp.start();
        if (!sharedPreferences.getBoolean("sp_volume", false)){
            mp.pause();
        }

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

        tv_options.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });


        sharedPreferences.registerOnSharedPreferenceChangeListener(new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
                if (s.equals("sp_volume")){
                    if (!sharedPreferences.getBoolean("sp_volume", false)){
                        mp.pause();
                    }else{
                        mp.start();
                    }
                }
            }
        });

    }

    @Override
    protected void onDestroy() {
        mp.stop();
        mp.release();
        super.onDestroy();
    }

}
