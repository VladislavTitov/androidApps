package com.example.vladislavtitov.mvpmvvmusers.presenters;

import android.content.Context;

import com.example.vladislavtitov.mvpmvvmusers.models.User;
import com.example.vladislavtitov.mvpmvvmusers.mvp.MvpActivityCallback;

import java.util.List;

public interface UsersPresenter {

    void setMvpActivityCallback(MvpActivityCallback mvpActivityCallback);

    void setContext(Context context);

    List<User> getUsers();

}
