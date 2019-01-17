package com.example.anubharadwaj.myapplication.view_models;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.example.anubharadwaj.myapplication.fragments.FlickrPhotoFeedFragment;

public class FlickrPhotoFeedViewModelFactory implements ViewModelProvider.Factory {
    private String query;


    public FlickrPhotoFeedViewModelFactory(FlickrPhotoFeedFragment flickrPhotoFeedFragment, String param) {
        query = param;
    }


    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new FlickrPhotoFeedViewModel(query);

    }
}
