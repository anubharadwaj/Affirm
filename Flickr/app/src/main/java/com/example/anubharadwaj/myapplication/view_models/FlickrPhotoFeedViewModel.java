package com.example.anubharadwaj.myapplication.view_models;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.PagedList;
import com.example.anubharadwaj.myapplication.database.entity.PhotoDetails;
import com.example.anubharadwaj.myapplication.repository.FlickrRepository;


public class FlickrPhotoFeedViewModel extends ViewModel {

    public LiveData<PagedList<PhotoDetails>> photoPagedList;

    public FlickrPhotoFeedViewModel(FlickrRepository flickrRepository, String query) {
        photoPagedList = flickrRepository.getPhotos(true, query);

    }

    public LiveData<PagedList<PhotoDetails>> getPhotoPagedList() {
        return photoPagedList;
    }
}
