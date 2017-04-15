package com.example.vladislavtitov.weatherwithcontentprovider.db.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.vladislavtitov.weatherwithcontentprovider.db.contracts.WeatherCityContract;

public class CitiesDbOpenHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "cities.db";
    public static final int VERSION = 1;

    public CitiesDbOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        WeatherCityContract.createTable(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
