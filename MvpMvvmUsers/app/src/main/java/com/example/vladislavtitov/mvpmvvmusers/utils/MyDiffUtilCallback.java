package com.example.vladislavtitov.mvpmvvmusers.utils;

import android.support.v7.util.DiffUtil;

import com.example.vladislavtitov.mvpmvvmusers.models.User;

import java.util.List;

public class MyDiffUtilCallback extends DiffUtil.Callback {

    private List<User> oldUsers;
    private List<User> newUsers;

    public MyDiffUtilCallback(List<User> oldUsers, List<User> newUsers) {
        this.oldUsers = oldUsers;
        this.newUsers = newUsers;
    }

    @Override
    public int getOldListSize() {
        return oldUsers.size();
    }

    @Override
    public int getNewListSize() {
        return newUsers.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldUsers.get(oldItemPosition).equals(newUsers.get(newItemPosition));
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldUsers.get(oldItemPosition).equals(newUsers.get(newItemPosition));
    }
}
