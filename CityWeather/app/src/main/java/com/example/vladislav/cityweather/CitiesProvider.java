package com.example.vladislav.cityweather;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CitiesProvider {
    private static CitiesProvider ourInstance = new CitiesProvider();

    public static CitiesProvider getInstance() {
        return ourInstance;
    }

    public static final String PREFS_NAME = "cityprefs";

    private List<String> cities;

    private CitiesProvider() {
        cities = new ArrayList<>();
    }

    public void recordCity(Context context, String cityName){
        SharedPreferences preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(cityName, cityName);
        editor.commit();
        cities.add(cityName);
    }

    public void restoreCity(Context context){
        cities.clear();
        SharedPreferences preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        Map<String, ?> map = preferences.getAll();
        for (Map.Entry<String, ?> entry:map.entrySet()){
            cities.add(entry.getKey());
        }
    }

    public List<String> getCities() {
        return cities;
    }
}
