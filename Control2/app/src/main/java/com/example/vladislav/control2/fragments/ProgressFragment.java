package com.example.vladislav.control2.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.example.vladislav.control2.callbacks.AsyncCallback;


public class ProgressFragment extends Fragment {

    AsyncCallback callback;
    ProgressAsync async;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        attachCallback(context);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        attachCallback(activity);
    }

    private void attachCallback(Context context){
        callback = (AsyncCallback) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    public void startTask(){
        if (async == null){
            async = new ProgressAsync();
            async.execute();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        async = null;
    }

    class ProgressAsync extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            callback.closeActivity();
        }
    }

}
