package com.example.vladislav.control2.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.example.vladislav.control2.callbacks.EditAsyncCallback;

public class EditAsyncFragment extends Fragment {

    EditAsyncCallback callback;
    EditAsyncTask editAsyncTask;


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
        callback = (EditAsyncCallback) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        startTask();
    }

    public void startTask(){
        if (editAsyncTask == null){
            editAsyncTask = new EditAsyncTask();
            editAsyncTask.execute();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callback = null;
    }

    class EditAsyncTask extends AsyncTask<Void, Void, Void>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            callback.setProgress(true);
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (callback != null) {
                callback.setProgress(false);
                callback.closeActivity();
            }
        }
    }
}
