package com.example.vladislavtitov.mvpmvvmusers.mvvm.viewModels;

import android.content.Context;
import android.databinding.ObservableField;

import com.example.vladislavtitov.mvpmvvmusers.models.User;
import com.example.vladislavtitov.mvpmvvmusers.mvvm.UserInfoMvvmActivity;
import com.example.vladislavtitov.mvpmvvmusers.storage.Users;

public class UserInfoViewModel {

    private UserInfoMvvmActivity context;
    private User user;
    private int position;

    public final ObservableField<String> name;
    public final ObservableField<String> surname;
    public final ObservableField<String> email;

    public UserInfoViewModel(UserInfoMvvmActivity context, int position) {
        this.context = context;
        this.position = position;
        user = Users.getInstance().getUsers().get(position);
        name = new ObservableField<>(user.getName());
        surname = new ObservableField<>(user.getSurname());
        email = new ObservableField<>(user.getEmail());
    }

    public void save(){
        Users.getInstance().getUsers().remove(position);
        user.setName(this.name.get());
        user.setSurname(this.surname.get());
        user.setEmail(this.email.get());
        Users.getInstance().getUsers().add(position, user);
        context.finish();
    }

}
