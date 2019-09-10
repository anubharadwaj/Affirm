package com.example.anubharadwaj.myapplication.repository;

import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;
import android.os.AsyncTask;
import com.example.anubharadwaj.myapplication.api.paging.NetworkPhotoDetailsDataSource;
import com.example.anubharadwaj.myapplication.api.paging.NetworkPhotoDetailsDataSourceFactory;
import com.example.anubharadwaj.myapplication.database.FlickrPhotoDatabase;
import com.example.anubharadwaj.myapplication.database.dao.FlickrPhotoDao;
import com.example.anubharadwaj.myapplication.database.entity.PhotoDetails;
import com.example.anubharadwaj.myapplication.database.paging.DBPhotoDetailsDataSourceFactory;
import com.example.anubharadwaj.myapplication.utils.Utility;
import java.util.List;

public class FlickrRepository {
    private FlickrPhotoDao flickrPhotoDao;
    private  LiveData<PagedList<PhotoDetails>> photos;

    LiveData<PageKeyedDataSource<Integer, PhotoDetails>> liveDataSourceFromNetwork;
    LiveData<PageKeyedDataSource<Integer, PhotoDetails>> liveDataSourceFromDB;

    public FlickrRepository() {
        FlickrPhotoDatabase flickrPhotoDatabase = FlickrPhotoDatabase.getInstance();
        flickrPhotoDao = flickrPhotoDatabase.photoDao();
    }

    public void savePhotos(List<PhotoDetails> photos) {
        for(PhotoDetails photo : photos){
            savePhoto(photo);
        }
    }

    public void savePhoto(PhotoDetails photoDetails) {
        new SavePhotoAsyncTask(flickrPhotoDao).execute(photoDetails);
    }

    public LiveData<PagedList<PhotoDetails>> getPhotos(boolean connected, String query) {
        NetworkPhotoDetailsDataSourceFactory photoDataSourceFactoryFromNetwork = new NetworkPhotoDetailsDataSourceFactory(query);
        DBPhotoDetailsDataSourceFactory photoDataSourceFactoryFromDB = new DBPhotoDetailsDataSourceFactory();
        liveDataSourceFromNetwork = photoDataSourceFactoryFromNetwork.getItemLiveDataSourceFromNetwork();
        liveDataSourceFromDB = photoDataSourceFactoryFromDB.getItemLiveDataSourceFromDB();
        PagedList.Config config =
                (new PagedList.Config.Builder())
                        .setEnablePlaceholders(true)
                        .setPageSize(NetworkPhotoDetailsDataSource.PAGE_SIZE)
                        .build();

        if(Utility.isNetworkAvailable() && connected){
            photos = (new LivePagedListBuilder(photoDataSourceFactoryFromNetwork, config)).build();
        } else{
            photos = (new LivePagedListBuilder(photoDataSourceFactoryFromDB, config)).build();
        }
        return photos;
    }

    private static class SavePhotoAsyncTask extends AsyncTask<PhotoDetails, Void, Void> {
        private FlickrPhotoDao flickrPhotoDao;

        private SavePhotoAsyncTask(FlickrPhotoDao flickrPhotoDao) {
            this.flickrPhotoDao = flickrPhotoDao;
        }

        @Override
        protected Void doInBackground(PhotoDetails... photos) {
            flickrPhotoDao.save(photos[0]);
            return null;
        }
    }
}
