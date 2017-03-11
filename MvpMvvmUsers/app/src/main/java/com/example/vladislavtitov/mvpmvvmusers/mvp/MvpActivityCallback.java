package com.example.vladislavtitov.mvpmvvmusers.mvp;

import com.example.vladislavtitov.mvpmvvmusers.models.User;

import java.util.List;

public interface MvpActivityCallback {

    void updateUsers(List<User> newUsers);

}
