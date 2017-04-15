package com.example.vladislavtitov.weatherwithcontentprovider;

import android.content.Context;
import android.database.Cursor;

import com.example.vladislavtitov.weatherwithcontentprovider.db.contracts.WeatherCityContract;
import com.example.vladislavtitov.weatherwithcontentprovider.db.models.WeatherCity;

import java.util.ArrayList;
import java.util.List;

class CitiesNamesProvider {
    private static final CitiesNamesProvider ourInstance = new CitiesNamesProvider();

    static CitiesNamesProvider getInstance() {
        return ourInstance;
    }

    private List<String> citiesNames;

    private CitiesNamesProvider() {
        citiesNames = new ArrayList<>();
    }

    public void queryCitiesNames(Context context) {
        queryNames(context);
    }

    public List<String> provideCitiesNames() {
        return citiesNames;
    }

    public void addCityName(Context context, String cityName) {
        this.citiesNames.add(cityName);
        context.getContentResolver().insert(WeatherCityContract.getBaseUri(), WeatherCityContract.toContentValues(new WeatherCity(cityName)));
    }

    private void queryNames(Context context) {
        Cursor cursor = context.getContentResolver().query(WeatherCityContract.getBaseUri(), null, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                citiesNames.add(WeatherCityContract.fromCursor(cursor).getName());
            }
            cursor.close();
        }
    }

}
