package com.example.vladislav.control2.activities;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.example.vladislav.control2.R;
import com.example.vladislav.control2.callbacks.AsyncCallback;
import com.example.vladislav.control2.fragments.ProgressFragment;

public class SplashActivity extends AppCompatActivity implements AsyncCallback {

    ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        progress = (ProgressBar) findViewById(R.id.progress);
        progress.setVisibility(ProgressBar.VISIBLE);

        if (savedInstanceState == null){
            getProgressFragment().startTask();
        }

    }

    private ProgressFragment getProgressFragment(){
        FragmentManager manager = getSupportFragmentManager();
        ProgressFragment fragment = (ProgressFragment) manager.findFragmentByTag(ProgressFragment.class.getName());

        if (fragment == null){
            fragment = new ProgressFragment();

            manager.beginTransaction()
                    .add(fragment, ProgressFragment.class.getName())
                    .commit();

        }
        return fragment;
    }

    @Override
    public void closeActivity() {
        progress.setVisibility(ProgressBar.INVISIBLE);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }


}
