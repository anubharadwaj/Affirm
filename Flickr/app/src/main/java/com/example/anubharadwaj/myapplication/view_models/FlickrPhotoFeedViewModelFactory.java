package com.example.anubharadwaj.myapplication.view_models;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.example.anubharadwaj.myapplication.repository.FlickrRepository;


public class FlickrPhotoFeedViewModelFactory implements ViewModelProvider.Factory{
    private FlickrRepository mRepo;
    private String mQuery;

    public FlickrPhotoFeedViewModelFactory(FlickrRepository repo, String query){
        mRepo = repo;
        mQuery = query;
    }

    @NonNull
    @Override
    public <T extends  ViewModel> T create(@NonNull Class<T> modelClass){
        if(modelClass.isAssignableFrom(FlickrPhotoFeedViewModel.class)){
            return (T) new FlickrPhotoFeedViewModel(mRepo, mQuery);
        }
        throw new IllegalArgumentException("Unknown ViewModel Class");
    }
}
