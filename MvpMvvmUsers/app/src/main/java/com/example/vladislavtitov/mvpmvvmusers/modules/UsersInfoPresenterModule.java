package com.example.vladislavtitov.mvpmvvmusers.modules;

import com.example.vladislavtitov.mvpmvvmusers.presenters.UserInfoPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class UsersInfoPresenterModule {

    UserInfoPresenter userInfoPresenter;

    public UsersInfoPresenterModule() {
        userInfoPresenter = new UserInfoPresenter();
    }

    @Provides
    UserInfoPresenter provideUserInfoPresenter(){
        return userInfoPresenter;
    }

}
