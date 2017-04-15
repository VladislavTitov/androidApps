package com.example.vladislavtitov.weatherwithcontentprovider;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

public class NewCityDialogCreator {

    public static AlertDialog createNewCityDialog(final AppCompatActivity activity){

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        builder
                .setTitle("Adding new city")
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (activity instanceof NewCityDialogCallback){
                            ((NewCityDialogCallback) activity).addNewCity();
                        }
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setView(activity.getLayoutInflater().inflate(R.layout.new_city_dialog_view, null))
                .setCancelable(true);

        return builder.create();

    }

}
