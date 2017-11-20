package com.example.mojiehua93.volleydemo;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by MOJIEHUA93 on 2017/11/20.
 */

public class MyApplication extends Application {
    public static RequestQueue sRequestQueue;

    @Override
    public void onCreate() {
        super.onCreate();
        sRequestQueue = Volley.newRequestQueue(getApplicationContext());
    }

    public static RequestQueue getRequestQueue() {
        return sRequestQueue;
    }
}
