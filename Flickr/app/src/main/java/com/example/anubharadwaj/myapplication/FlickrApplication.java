package com.example.anubharadwaj.myapplication;

import android.app.Application;

public class FlickrApplication extends Application {

    private static FlickrApplication mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    public static FlickrApplication getContext() {
        return mContext;
    }
}