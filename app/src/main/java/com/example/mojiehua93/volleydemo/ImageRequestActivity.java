package com.example.mojiehua93.volleydemo;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;

/**
 * Created by MOJIEHUA93 on 2017/11/25.
 */

public class ImageRequestActivity extends AppCompatActivity {

    public static final String TAG = "ImageRequestActivity";
    private static String sUrl = "https://www.baidu.com/img/bdlogo.png";

    private ImageView mImageView;
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;
    private ImageLoader.ImageListener mImageListener;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_request);
        mRequestQueue = MyApplication.getRequestQueue();
        initView();
//        volleyImageRequest(sUrl);
        volleyImageLoader(sUrl);
    }

    private void volleyImageLoader(String url) {
        mImageLoader = new ImageLoader(mRequestQueue, new BitmapCache());
        mImageListener = ImageLoader.getImageListener(mImageView, R.mipmap.ic_launcher,
                R.mipmap.ic_launcher_round);
        mImageLoader.get(url, mImageListener);
    }

    private void volleyImageRequest(String url) {
        ImageRequest imageRequest = new ImageRequest(url, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                if (response != null) {
                    mImageView.setImageBitmap(response);
                }
            }
        }, 0, 0, null, Bitmap.Config.RGB_565,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        mImageView.setImageResource(R.mipmap.ic_launcher);
                        VolleyResponse.doErrorResponse(getApplicationContext(), error.toString(),
                                TAG);
                    }
                });
        imageRequest.setTag(url);
        mRequestQueue.add(imageRequest);
    }

    private void initView() {
        mImageView = findViewById(R.id.iv_image_request);
    }
}
