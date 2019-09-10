package com.example.anubharadwaj.myapplication.view_models;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;


public class FlickrPhotoFeedViewModelFactory implements ViewModelProvider.Factory{
    String query;
    public FlickrPhotoFeedViewModelFactory(String query){
        this.query = query;
    }

    @NonNull
    @Override
    public <T extends  ViewModel> T create(@NonNull Class<T> modelClass){
       if(modelClass.isAssignableFrom(FlickrPhotoFeedViewModel.class)){
            return (T) new FlickrPhotoFeedViewModel(query);
        }
        throw new IllegalArgumentException("Unknown ViewModel Class");
    }
}
