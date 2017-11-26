package com.example.mojiehua93.volleydemo;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.toolbox.ImageLoader;

/**
 * Created by MOJIEHUA93 on 2017/11/26.
 */

public class BitmapCache implements ImageLoader.ImageCache {
    private LruCache<String, Bitmap> mBitmapLruCache;

    public BitmapCache() {
        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        int cacheSize = maxMemory / 4;
        mBitmapLruCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes() * value.getHeight();
            }
        };
    }

    @Override
    public Bitmap getBitmap(String url) {
        return mBitmapLruCache.get(url);
    }

    @Override
    public void putBitmap(String url, Bitmap bitmap) {
        if (mBitmapLruCache != null) {
            mBitmapLruCache.put(url, bitmap);
        }
    }
}
