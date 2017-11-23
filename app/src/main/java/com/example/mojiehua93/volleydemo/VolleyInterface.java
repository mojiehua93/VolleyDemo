package com.example.mojiehua93.volleydemo;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.VolleyError;

/**
 * Created by MOJIEHUA93 on 2017/11/23.
 */

public abstract class VolleyInterface {

    private Context mContext;
    public static Response.Listener<String> mListener;
    public static Response.ErrorListener mErrorListener;

    public VolleyInterface(Context context, Response.Listener<String> listener,
            Response.ErrorListener errorListener) {
        mContext = context;
        mListener = listener;
        mErrorListener = errorListener;
    }

    public abstract void onRequestSuccess(String response);

    public abstract void onRequestError(VolleyError error);

    public Response.Listener<String> getListener() {
        mListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                onRequestSuccess(response);
            }
        };
        return mListener;
    }

    public Response.ErrorListener getErrorListener() {
        mErrorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                onRequestError(error);
            }
        };
        return mErrorListener;
    }
}
