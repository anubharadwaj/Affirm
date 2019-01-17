package com.example.anubharadwaj.myapplication.api.paging;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.DataSource;
import android.arch.paging.PageKeyedDataSource;

import com.example.anubharadwaj.myapplication.database.entity.PhotoDetails;

public class NetworkPhotoDetailsDataSourceFactory extends DataSource.Factory {

    private String query;
    private MutableLiveData<PageKeyedDataSource<Integer, PhotoDetails>> itemLiveDataSource = new MutableLiveData<>();

    public NetworkPhotoDetailsDataSourceFactory(String query) {
        this.query = query;
    }


    @Override
    public DataSource create() {
        NetworkPhotoDetailsDataSource photoDataSourceFromNetwork = new NetworkPhotoDetailsDataSource(query);
        itemLiveDataSource.postValue(photoDataSourceFromNetwork);
        return photoDataSourceFromNetwork;
    }

    public MutableLiveData<PageKeyedDataSource<Integer, PhotoDetails>> getItemLiveDataSourceFromNetwork() {
        return itemLiveDataSource;
    }
}


