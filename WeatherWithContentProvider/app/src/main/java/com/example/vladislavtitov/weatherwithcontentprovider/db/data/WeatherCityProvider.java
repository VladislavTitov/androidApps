package com.example.vladislavtitov.weatherwithcontentprovider.db.data;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.vladislavtitov.weatherwithcontentprovider.db.contracts.WeatherCityContract;


public class WeatherCityProvider extends ContentProvider{

    private CitiesDbOpenHelper openHelper;
    public static final String CONTENT_AUTHORITY = "com.example.vladislavtitov.weatherwithcontentprovider";
    public static final int CITIES_KEYS_MATCHES = 101;
    public static Uri baseUri;
    private UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);


    @Override
    public boolean onCreate() {
        Log.d("my_logs", "WeatherCityProvider.onCreate(), getContext != null: " + (getContext() != null));
        if (getContext() != null){
            openHelper = new CitiesDbOpenHelper(getContext());
            baseUri = Uri.parse("content://" + CONTENT_AUTHORITY);
            uriMatcher.addURI(CONTENT_AUTHORITY, WeatherCityContract.TABLE_NAME, CITIES_KEYS_MATCHES);
            return true;
        }
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        String table = getType(uri);
        return openHelper.getReadableDatabase().query(table, projection, selection, selectionArgs, null, null, null);
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        int matchCode = uriMatcher.match(uri);
        if (matchCode == CITIES_KEYS_MATCHES){
            return WeatherCityContract.TABLE_NAME;
        }
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        String table = getType(uri);
        openHelper.getWritableDatabase().insert(table, null, values);
        return uri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
