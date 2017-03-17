package com.example.vladislavtitov.randomusermvvm.utils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.vladislavtitov.randomusermvvm.api.UsersProvideService;
import com.example.vladislavtitov.randomusermvvm.models.Result;
import com.example.vladislavtitov.randomusermvvm.models.Users;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class UsersProvider {

    public static final String PREFS_NAME = "users_storage";

    public static void loadUsers(Context context){
        Intent intent = new Intent(context, UsersProvideService.class);
        context.startService(intent);
    }

    public static List<Result> getUsers(Context context){
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String usersJson = prefs.getString("users", "empty");
        Log.d("my_logs", "getUsers()");
        Gson gson = new Gson();
        Users users;
        try {
            users = gson.fromJson(usersJson, Users.class);
        }catch (Exception e){
            e.printStackTrace();
            return new ArrayList<>();
        }
        return users.getResults();
    }

}
