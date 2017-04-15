package com.example.vladislavtitov.weatherwithcontentprovider.db.contracts;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.BaseColumns;

import com.example.vladislavtitov.weatherwithcontentprovider.db.data.WeatherCityProvider;
import com.example.vladislavtitov.weatherwithcontentprovider.db.models.WeatherCity;

public class WeatherCityContract {

    public static final String TABLE_NAME = "cities";

    public static void createTable(SQLiteDatabase db){
        TableBuilder.create(TABLE_NAME)
                .textColumn(WeatherCityEntry.COLUMN_CITY_NAME)
                .primaryKey(WeatherCityEntry.COLUMN_CITY_NAME)
                .execute(db);
    }

    public static ContentValues toContentValues(WeatherCity weatherCity){
        ContentValues contentValues = new ContentValues();
        contentValues.put(WeatherCityEntry.COLUMN_CITY_NAME, weatherCity.getName());
        return contentValues;
    }

    public static WeatherCity fromCursor(Cursor cursor){
        if (cursor == null){
            return null;
        }
        int name_index = cursor.getColumnIndex(WeatherCityEntry.COLUMN_CITY_NAME);
        return new WeatherCity(cursor.getString(name_index));
    }

    public static Uri getBaseUri(){
        return WeatherCityProvider.baseUri.buildUpon().appendPath(TABLE_NAME).build();
    }

    public static final class WeatherCityEntry implements BaseColumns{
        public static final String COLUMN_CITY_NAME = "city_name";
    }

}
