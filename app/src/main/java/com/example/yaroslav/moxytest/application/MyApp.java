package com.example.yaroslav.moxytest.application;

import android.app.Application;

import com.facebook.AccessToken;
import com.facebook.FacebookSdk;

/**
 * Created by yaroslav on 03.11.16.
 */

public class MyApp extends Application {
    static MyApp mMyApp;


    @Override
    public void onCreate() {
        super.onCreate();
        mMyApp = this;
        FacebookSdk.sdkInitialize(getApplicationContext());
    }

    public static MyApp getMyApp(){
        if (mMyApp == null){
            synchronized (MyApp.class) {
                if (mMyApp == null)
                    mMyApp = new MyApp();
            }
        }
        return mMyApp;
    }
}
