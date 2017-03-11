package com.example.vladislavtitov.mvpmvvmusers.modules;

import com.example.vladislavtitov.mvpmvvmusers.presenters.UsersPresenter;
import com.example.vladislavtitov.mvpmvvmusers.presenters.impl.UsersPresenterImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class UsersPresenterModule {

    UsersPresenter usersPresenter;

    public UsersPresenterModule() {
        usersPresenter = new UsersPresenterImpl();
    }

    @Provides
    public UsersPresenter provideUsersPresenter(){
        return usersPresenter;
    }

}
