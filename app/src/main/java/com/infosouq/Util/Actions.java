package com.infosouq.Util;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.support.v7.app.AlertDialog;
import android.util.DisplayMetrics;
import android.util.LruCache;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by cry13 on 10/30/15.
 */
public class Actions {
    private Context context;
    private SharedPreferences.Editor editor;
    private SharedPreferences prefs;

    private static RequestQueue mRequestQueue;
    private static ImageLoader mImageLoader;

    public AlertDialog dialogDetails;
    private static  ImageLoader.ImageCache imageCache;
    private static ImageLoader imageLoader;
    public Actions(Context context){
        this.context=context;
        editor = context.getSharedPreferences("userdetails", context.MODE_PRIVATE).edit();
        prefs = context.getSharedPreferences("userdetails", context.MODE_PRIVATE);
        imageCache = new BitmapLruCache();
    }

    public boolean loginStatus(){
        if (prefs.getString("username",null)!=null){
            return true;
        }
        return false;
    }
    public String getUsername(){
        return prefs.getString("username",null);
    }
    public String getPassword(){
        return prefs.getString("password",null);
    }

    public String convertDate(String informat, String outformat,String date){
        DateFormat  shortFormat = new SimpleDateFormat(informat, Locale.ENGLISH);
        DateFormat mediumFormat = new SimpleDateFormat(outformat,Locale.ENGLISH);
        try {
            String shortDate = mediumFormat.format(shortFormat.parse(date));
            return  shortDate;
        }catch (ParseException e){
            return "N/A";
        }


    }
    public static ImageLoader getmImageLoader(Context context){

        mRequestQueue = Volley.newRequestQueue(context);

        mImageLoader = new ImageLoader(mRequestQueue, new ImageLoader.ImageCache() {
            private final LruCache<String, Bitmap> mCache = new LruCache<String, Bitmap>(10);
            public void putBitmap(String url, Bitmap bitmap) {
                mCache.put(url, bitmap);
            }
            public Bitmap getBitmap(String url) {

                return mCache.get(url);
            }
        });

        return mImageLoader;

    }
    public static ImageLoader getImageLoaderBitmapLRU(Context context){


        imageLoader = new ImageLoader(Volley.newRequestQueue(context), imageCache);
        return imageLoader;

    }
    public ImageLoader getmImageFromBitmap(Context context,final Bitmap bitmap){

    /*    mRequestQueue = Volley.newRequestQueue(context);
        mImageLoader = new ImageLoader(mRequestQueue, new ImageLoader.ImageCache() {

            public void putBitmap(String url, Bitmap bitmap) {
                //mCache.put(url, bitmap);
            }
            public Bitmap getBitmap(String url) {

                return bitmap;
            }
        });
*/

        MyCache myCache = new MyCache(bitmap);
        mImageLoader = new ImageLoader(null, myCache);

        return mImageLoader;

    }
    public class MyCache implements ImageLoader.ImageCache {
        Bitmap bitmap;
        MyCache(Bitmap bitmap){
            this.bitmap=bitmap;
        }
        @Override
        public Bitmap getBitmap(String key) {
            return bitmap;
        }
        @Override
        public void putBitmap(String key, Bitmap bitmap) {
            // Here you can add an actual cache
        }
    }

    public int getScreenType(){
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        int densityDpi = (int)(metrics.density * 160f);

        return densityDpi;
    }



}

