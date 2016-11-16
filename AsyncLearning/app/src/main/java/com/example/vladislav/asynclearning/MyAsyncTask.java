package com.example.vladislav.asynclearning;

import android.os.AsyncTask;
import android.util.Log;

public class MyAsyncTask extends AsyncTask<Void, Void, Boolean> {

    public static final String MY_TAG = "mytag";
    @Override
    protected Boolean doInBackground(Void... params) {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.d(MY_TAG, "This is MyAsyncTask!");
        return null;
    }
}
