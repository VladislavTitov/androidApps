package com.example.vladislavtitov.mvpmvvmusers.presenters.impl;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.example.vladislavtitov.mvpmvvmusers.models.User;
import com.example.vladislavtitov.mvpmvvmusers.mvp.MvpActivityCallback;
import com.example.vladislavtitov.mvpmvvmusers.presenters.UsersPresenter;
import com.example.vladislavtitov.mvpmvvmusers.storage.Users;

import java.util.List;

public class UsersPresenterImpl implements UsersPresenter {

    private MvpActivityCallback mvpActivityCallback;

    Context context;

    public UsersPresenterImpl() {
    }

    @Override
    public void setMvpActivityCallback(@NonNull MvpActivityCallback mvpActivityCallback) {
        this.mvpActivityCallback = mvpActivityCallback;
    }

    @Override
    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public List<User> getUsers() {
        return Users.getInstance().getUsers();
    }

}
