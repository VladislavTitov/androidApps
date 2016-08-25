package com.example.vladislav.historybyplaces;

import android.database.Cursor;
import android.database.SQLException;
import android.support.v7.app.AppCompatActivity;

import java.io.IOException;

public class DBHelperRealtor {

    public static Cursor prepareDB(AppCompatActivity activity){
        DBHelper dbhelper = new DBHelper(activity);
        try {
            dbhelper.createDataBase();
        } catch (IOException ioe) {
            throw new Error("Unable to create database");
        }
        try {
            dbhelper.openDataBase();
        } catch (SQLException sqle) {
            throw sqle;
        }

        Cursor cursor = dbhelper.query();
        return cursor;
    }
}
