package com.example.mojiehua93.volleydemo;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.ArrayMap;
import android.util.Log;
import android.util.SparseArray;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;

import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static String sUrl = "http://www.imooc.com/api/teacher?type=4&num=30";
    private RequestQueue mRequestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRequestQueue = MyApplication.getRequestQueue();
//        volleyStringRequestGet(sUrl);
//        volleyJsonObjectGet(sUrl);
        volleyStringRequestPost(sUrl);
    }

    private void volleyStringRequestPost(String url) {
        String urlPost = url.substring(0,url.lastIndexOf('?') + 1);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, urlPost,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getApplicationContext(), response,
                                Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(),
                        Toast.LENGTH_LONG).show();
            }
        }) {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                ArrayMap<String, String> map = new ArrayMap<>();
                map.put("type", "4");
                map.put("num", "30");
                return map;
            }
        };
        stringRequest.setTag(urlPost);
        mRequestQueue.add(stringRequest);
    }

    private void volleyJsonObjectGet(String url) {
        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(getApplicationContext(), response.toString(),
                                Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.toString(),
                                Toast.LENGTH_LONG).show();
                    }
                });
        objectRequest.setTag(url);
        mRequestQueue.add(objectRequest);
    }

    private void volleyStringRequestGet(String url) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, "onResponse: response = " + response);
                        Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG)
                                .show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG)
                        .show();
            }
        });
        stringRequest.setTag(url);
        mRequestQueue.add(stringRequest);
    }


}
