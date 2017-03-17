package com.example.vladislavtitov.mvpmvvmusers.mvvm.viewModels;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableField;

import com.example.vladislavtitov.mvpmvvmusers.models.User;
import com.example.vladislavtitov.mvpmvvmusers.mvvm.MvvmActivity;
import com.example.vladislavtitov.mvpmvvmusers.mvvm.UserInfoMvvmActivity;

public class UserItemViewModel extends BaseObservable{

    private User user;
    private int position;
    private MvvmActivity context;
    public final ObservableField<String> fullname = new ObservableField<>();

    public UserItemViewModel() {
    }

    public UserItemViewModel(MvvmActivity context) {
        this.context = context;
    }

    public void setUser(User user) {
        this.user = user;
        fullname.set(user.getSurname() + " " + user.getName());
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void onItemClick(){
        UserInfoMvvmActivity.launchUserInfoActivity(context, position);
    }
}
