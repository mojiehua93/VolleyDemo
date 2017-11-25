package com.example.mojiehua93.volleydemo;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by MOJIEHUA93 on 2017/11/25.
 */

public class VolleyResponse {
    public static void doResponse(Context context, String response, String tag) {
        Toast.makeText(context, response, Toast.LENGTH_LONG).show();
        Log.d(tag, "doResponse: " + response);
    }

    public static void doErrorResponse(Context context, String error, String tag) {
        Toast.makeText(context, error, Toast.LENGTH_LONG).show();
        Log.d(tag, "doErrorResponse: " + error);
    }
}
