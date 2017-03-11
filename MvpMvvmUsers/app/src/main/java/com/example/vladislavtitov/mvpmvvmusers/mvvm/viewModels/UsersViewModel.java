package com.example.vladislavtitov.mvpmvvmusers.mvvm.viewModels;

import android.content.Context;

import com.example.vladislavtitov.mvpmvvmusers.models.User;
import com.example.vladislavtitov.mvpmvvmusers.storage.Users;

import java.util.List;
import java.util.Observable;

public class UsersViewModel extends Observable{

    public UsersViewModel() {

    }

    public List<User> getUsers() {
        return Users.getInstance().getUsers();
    }
}
