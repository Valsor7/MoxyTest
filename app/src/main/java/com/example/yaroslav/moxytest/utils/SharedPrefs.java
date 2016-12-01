package com.example.yaroslav.moxytest.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.yaroslav.moxytest.application.MyApp;

/**
 * Created by yaroslav on 16.11.16.
 */

public class SharedPrefs {
    private static final String TAG = SharedPrefs.class.getSimpleName();

    public static void saveStrParam(String key, String value){
        final SharedPreferences prefs = MyApp.getMyApp().getSharedPreferences(
                "my_preferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static String loadStrParam(String key){
        final SharedPreferences prefs = MyApp.getMyApp().getSharedPreferences(
                "my_preferences", Context.MODE_PRIVATE);
        prefs.getString(key, "");
        String value = prefs.getString(key, "");

        if (value.isEmpty()) {
            Log.i(TAG, "Param not found.");
            return "";
        }
        return value;
    }
}
