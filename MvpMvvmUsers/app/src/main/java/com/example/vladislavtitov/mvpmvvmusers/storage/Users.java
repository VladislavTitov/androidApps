package com.example.vladislavtitov.mvpmvvmusers.storage;

import com.example.vladislavtitov.mvpmvvmusers.models.User;

import java.util.ArrayList;
import java.util.List;

public class Users {
    private static final Users ourInstance = new Users();

    public static Users getInstance() {
        return ourInstance;
    }

    private List<User> users;

    private Users() {
        users = new ArrayList<>();
        users.add(new User("vlad", "titov", "vladtitovmail"));
        users.add(new User("rustam", "nurg", "rustamnurgmail"));
        users.add(new User("tamerlan", "shak", "tamerlanmail"));
        users.add(new User("yura", "han", "yurahanmail"));
        users.add(new User("renat", "gaf", "renatmail"));
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
