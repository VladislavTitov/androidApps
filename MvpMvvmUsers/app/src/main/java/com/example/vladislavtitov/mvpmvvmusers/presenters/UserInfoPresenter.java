package com.example.vladislavtitov.mvpmvvmusers.presenters;

import com.example.vladislavtitov.mvpmvvmusers.models.User;
import com.example.vladislavtitov.mvpmvvmusers.storage.Users;

public class UserInfoPresenter {

    public void save(int position, User user){
        Users.getInstance().getUsers().remove(position);
        Users.getInstance().getUsers().add(position, user);
    }

    public User getUser(int pos){
        return Users.getInstance().getUsers().get(pos);
    }

}
