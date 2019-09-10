package com.example.anubharadwaj.myapplication.view_models;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;
import com.example.anubharadwaj.myapplication.FlickrApplication;
import com.example.anubharadwaj.myapplication.database.entity.PhotoDetails;
import com.example.anubharadwaj.myapplication.repository.FlickrRepository;
import java.util.List;

public class FlickrPhotoFeedViewModel extends AndroidViewModel {
    private FlickrRepository repository;
    private LiveData<PagedList<PhotoDetails>> photos;

    public FlickrPhotoFeedViewModel(String query) {
        super(FlickrApplication.getContext());
        repository = new FlickrRepository();
        photos = repository.getPhotos(true, query);
    }

    public void savePhoto(List<PhotoDetails> photoDetails) {
        repository.savePhotos(photoDetails);
    }

    public LiveData<PagedList<PhotoDetails>> loadPhotos() {
        return photos;
    }
}
