package com.example.vladislavtitov.randomusermvvm.viewModels;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.vladislavtitov.randomusermvvm.models.Result;
import com.example.vladislavtitov.randomusermvvm.utils.UsersProvider;

import java.util.List;
import java.util.Observable;

public class MainViewModel extends Observable{

    private Context context;

    public MainViewModel() {
    }

    public MainViewModel(Context context) {
        this.context = context;
    }

    public List<Result> getUsers(){
        return UsersProvider.getUsers(context);
    }

    public boolean hasConnection()
    {
        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (wifiInfo != null && wifiInfo.isConnected()) {
            return true;
        }
        wifiInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (wifiInfo != null && wifiInfo.isConnected()) {
            return true;
        }
        wifiInfo = cm.getActiveNetworkInfo();
        if (wifiInfo != null && wifiInfo.isConnected()) {
            return true;
        }
        return false;
    }

}
