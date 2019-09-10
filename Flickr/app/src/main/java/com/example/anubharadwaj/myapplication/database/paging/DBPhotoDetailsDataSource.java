package com.example.anubharadwaj.myapplication.database.paging;

import androidx.lifecycle.LiveData;
import androidx.paging.PageKeyedDataSource;
import androidx.annotation.NonNull;
import com.example.anubharadwaj.myapplication.database.FlickrPhotoDatabase;
import com.example.anubharadwaj.myapplication.database.dao.FlickrPhotoDao;
import com.example.anubharadwaj.myapplication.database.entity.PhotoDetails;
import java.util.List;

public class DBPhotoDetailsDataSource extends PageKeyedDataSource<Integer,PhotoDetails> {
    private final FlickrPhotoDao photoDao;

    public DBPhotoDetailsDataSource() {
        FlickrPhotoDatabase flickrPhotoDatabase = FlickrPhotoDatabase.getInstance();
        this.photoDao = flickrPhotoDatabase.photoDao();
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, PhotoDetails> callback) {
        LiveData<List<PhotoDetails>> photos = photoDao.loadPhotos();
        if(photos.getValue().size() != 0) {
            callback.onResult(photos.getValue(), 0, 1);
        }
    }


    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, PhotoDetails> callback) {
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, PhotoDetails> callback) {


    }
}



