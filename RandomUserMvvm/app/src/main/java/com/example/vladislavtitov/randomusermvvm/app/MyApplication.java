package com.example.vladislavtitov.randomusermvvm.app;

import android.app.Application;
import android.util.Log;

import com.example.vladislavtitov.randomusermvvm.utils.UsersProvider;


public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        if (getApplicationContext().getSharedPreferences(UsersProvider.PREFS_NAME, MODE_PRIVATE).getString("users", "empty").equals("empty")){
            Log.d("my_logs", "Application.onCreate()");
            UsersProvider.loadUsers(getApplicationContext());
        }
    }

}
