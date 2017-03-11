package com.example.vladislavtitov.mvpmvvmusers.app;

import android.app.Application;

import com.example.vladislavtitov.mvpmvvmusers.components.DaggerUsersComponent;
import com.example.vladislavtitov.mvpmvvmusers.modules.UsersInfoPresenterModule;
import com.example.vladislavtitov.mvpmvvmusers.modules.UsersPresenterModule;
import com.example.vladislavtitov.mvpmvvmusers.components.UsersComponent;

public class MyApplication extends Application {

    UsersComponent usersComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        usersComponent = DaggerUsersComponent.builder()
                .usersPresenterModule(new UsersPresenterModule())
                .usersInfoPresenterModule(new UsersInfoPresenterModule())
                .build();

    }

    public UsersComponent getUsersComponent(){
        return usersComponent;
    }
}
