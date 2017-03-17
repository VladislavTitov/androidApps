package com.example.vladislavtitov.randomusermvvm.api;

import android.app.IntentService;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.vladislavtitov.randomusermvvm.utils.UsersProvider;

import java.io.IOException;

import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class UsersProvideService extends IntentService {

    public UsersProvideService() {
        super("UsersProvideService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String users;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://randomuser.me")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        UsersQueries usersQueries = retrofit.create(UsersQueries.class);
        try {
            Response<String> response = usersQueries.getUsers().execute();
            Log.d("my_logs", "Success of response: " + response.isSuccessful());
            if (response.isSuccessful()){
                users = response.body();
                /*Log.d("my_logs", "Body of response: " + users);*/
            }else {
                users = "error";
            }
        } catch (IOException e) {
            e.printStackTrace();
            users = "exception";
        }

        SharedPreferences.Editor prefs = getApplicationContext().getSharedPreferences(UsersProvider.PREFS_NAME, MODE_PRIVATE).edit();
        prefs
                .putString("users", users)
                .apply();

    }

}
