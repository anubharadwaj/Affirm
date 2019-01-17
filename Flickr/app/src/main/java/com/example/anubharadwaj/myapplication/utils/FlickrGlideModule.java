package com.example.anubharadwaj.myapplication.utils;

import android.content.Context;

import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.request.RequestOptions;

@GlideModule
public class FlickrGlideModule extends AppGlideModule {
    @Override
    public void applyOptions(Context context, GlideBuilder builder) {

        builder.setDefaultRequestOptions(new RequestOptions().transforms(new CenterCrop(),new RoundedCorners(50)).format(DecodeFormat.PREFER_ARGB_8888.PREFER_ARGB_8888));
    }
}
