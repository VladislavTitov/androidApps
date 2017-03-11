package com.example.vladislavtitov.mvpmvvmusers.components;

import com.example.vladislavtitov.mvpmvvmusers.modules.UsersInfoPresenterModule;
import com.example.vladislavtitov.mvpmvvmusers.modules.UsersPresenterModule;
import com.example.vladislavtitov.mvpmvvmusers.presenters.UserInfoPresenter;
import com.example.vladislavtitov.mvpmvvmusers.presenters.UsersPresenter;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {UsersPresenterModule.class, UsersInfoPresenterModule.class})
public interface UsersComponent {

    UsersPresenter usersPresenter();

    UserInfoPresenter usersInfoPresenter();

}
