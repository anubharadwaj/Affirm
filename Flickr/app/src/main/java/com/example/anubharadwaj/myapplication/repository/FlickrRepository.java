package com.example.anubharadwaj.myapplication.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PageKeyedDataSource;
import android.arch.paging.PagedList;


import com.example.anubharadwaj.myapplication.api.paging.NetworkPhotoDetailsDataSource;
import com.example.anubharadwaj.myapplication.api.paging.NetworkPhotoDetailsDataSourceFactory;
import com.example.anubharadwaj.myapplication.database.entity.PhotoDetails;
import com.example.anubharadwaj.myapplication.database.paging.DBPhotoDetailsDataSourceFactory;
import com.example.anubharadwaj.myapplication.utils.Utility;

public class FlickrRepository {
    private static String query="";

    private static FlickrRepository mInstance = new FlickrRepository();
    public LiveData<PagedList<PhotoDetails>> photoPagedList;
    LiveData<PageKeyedDataSource<Integer, PhotoDetails>> liveDataSourceFromNetwork;
    LiveData<PageKeyedDataSource<Integer, PhotoDetails>> liveDataSourceFromDB;

    private final LiveData<PagedList<PhotoDetails>> photos = new MediatorLiveData<>();

    private FlickrRepository() {
    }

    public static synchronized FlickrRepository getInstance(){
        return mInstance;
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
            photoPagedList = (new LivePagedListBuilder(photoDataSourceFactoryFromNetwork, config)).build();
        } else{
            photoPagedList = (new LivePagedListBuilder(photoDataSourceFactoryFromDB, config)).build();
        }
        return photoPagedList;
    }
}
