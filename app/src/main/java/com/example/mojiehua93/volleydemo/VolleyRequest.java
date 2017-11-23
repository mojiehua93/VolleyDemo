package com.example.mojiehua93.volleydemo;

import android.content.Context;
import android.util.ArrayMap;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;

import java.util.Map;

/**
 * Created by MOJIEHUA93 on 2017/11/22.
 */

public class VolleyRequest {

    private static StringRequest sStringRequest;
    private static Context mContext;
    public static void requestGet(Context context, String url, String tag,
            VolleyInterface volleyInterface) {
        MyApplication.getRequestQueue().cancelAll(tag);
        mContext = context;
        sStringRequest = new StringRequest(Request.Method.GET, url, volleyInterface.getListener(),
                volleyInterface.getErrorListener());
        sStringRequest.setTag(tag);
        MyApplication.getRequestQueue().add(sStringRequest);
        MyApplication.getRequestQueue().start();
    }

    public static void requestPost(Context context, String url, String tag,
            final Map<String, String> map, VolleyInterface volleyInterface) {
        MyApplication.getRequestQueue().cancelAll(tag);
        mContext = context;
        sStringRequest = new StringRequest(Request.Method.POST, url, volleyInterface.getListener(),
                volleyInterface.getErrorListener()) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return map;
            }
        };
        sStringRequest.setTag(tag);
        MyApplication.getRequestQueue().add(sStringRequest);
        MyApplication.getRequestQueue().start();
    }
}
