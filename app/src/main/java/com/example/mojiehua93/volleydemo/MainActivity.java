package com.example.mojiehua93.volleydemo;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.ArrayMap;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static String sUrl = "http://www.imooc.com/api/teacher?type=4&num=30";
    private RequestQueue mRequestQueue;
    private Button mImageRequestButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRequestQueue = MyApplication.getRequestQueue();
        initView();
//        volleyStringRequestGet(sUrl);
//        volleyJsonObjectGet(sUrl);
//        volleyStringRequestPost(sUrl);
//        volleyJsonObjectPost(sUrl);
        volleyCustomStringRequestGet(sUrl);
    }

    private void initView() {
        findViewById(R.id.image_request).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ImageRequestActivity.class);
                startActivity(intent);
            }
        });
    }

    private void volleyCustomStringRequestGet(String url) {
        VolleyRequest.requestGet(getApplicationContext(), url, "string_get",
                new VolleyInterface(getApplicationContext(), VolleyInterface.mListener,
                        VolleyInterface.mErrorListener) {
            @Override
            public void onRequestSuccess(String response) {
                VolleyResponse.doResponse(getApplicationContext(), response, TAG);
            }

            @Override
            public void onRequestError(VolleyError error) {
                VolleyResponse.doErrorResponse(getApplicationContext(), error.toString(), TAG);
            }
        });
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void volleyJsonObjectPost(String url) {
        String urlPost = url.substring(0, url.lastIndexOf('?') + 1);
        ArrayMap<String, String> map = new ArrayMap<>();
        map.put("type", "4");
        map.put("num", "30");
        JSONObject object = new JSONObject(map);
        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.POST, urlPost,
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                VolleyResponse.doResponse(getApplicationContext(), response.toString(), TAG);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyResponse.doErrorResponse(getApplicationContext(), error.toString(),
                        TAG);
            }
        });
        objectRequest.setTag(sUrl);
        mRequestQueue.add(objectRequest);
    }

    private void volleyStringRequestPost(String url) {
        String urlPost = url.substring(0,url.lastIndexOf('?') + 1);
        Log.d(TAG, "volleyStringRequestPost: uroPost = " + urlPost);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, urlPost,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        VolleyResponse.doResponse(getApplicationContext(), response,
                                TAG);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyResponse.doErrorResponse(getApplicationContext(), error.toString(),
                        TAG);
            }
        }) {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                ArrayMap<String, String> map = new ArrayMap<>();
                map.put("type", "4");
                map.put("num", "30");
//                Map<String, String> map = new HashMap<String, String>();
//                map.put("type", "4");
//                map.put("num", "30");
                return map;
            }
        };
        stringRequest.setTag(sUrl);
        mRequestQueue.add(stringRequest);
    }

    private void volleyJsonObjectGet(String url) {
        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        VolleyResponse.doResponse(getApplicationContext(), response.toString(),
                                TAG);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyResponse.doErrorResponse(getApplicationContext(), error.toString(),
                                TAG);
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
                        VolleyResponse.doResponse(getApplicationContext(), response,
                                TAG);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyResponse.doErrorResponse(getApplicationContext(), error.toString(),
                        TAG);
            }
        });
        stringRequest.setTag(sUrl);
        mRequestQueue.add(stringRequest);
    }

//    private void doResponse(String response) {
//        Toast.makeText(getApplicationContext(), response,
//                Toast.LENGTH_LONG).show();
//        Log.d(TAG, "onResponse: response = " + response.toString());
//    }
//
//    private void doErrorResponse(String error) {
//        Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG)
//                .show();
//        Log.d(TAG, "doErrorResponse: error = " + error);
//    }

    @Override
    protected void onStop() {
        super.onStop();
        mRequestQueue.cancelAll(sUrl);
    }
}
