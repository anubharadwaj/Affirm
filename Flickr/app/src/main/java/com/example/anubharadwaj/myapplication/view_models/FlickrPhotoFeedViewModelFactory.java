package com.example.anubharadwaj.myapplication.view_models;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.annotation.NonNull;


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
