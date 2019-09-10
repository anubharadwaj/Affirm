package com.example.anubharadwaj.myapplication.database.paging;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;
import com.example.anubharadwaj.myapplication.database.entity.PhotoDetails;


public class DBPhotoDetailsDataSourceFactory extends DataSource.Factory {

    private MutableLiveData<PageKeyedDataSource<Integer, PhotoDetails>> itemLiveDataSource = new MutableLiveData<>();


    @Override
    public DataSource create() {
        DBPhotoDetailsDataSource photoDataSourceFromDB = new DBPhotoDetailsDataSource();
        itemLiveDataSource.postValue(photoDataSourceFromDB);
        return photoDataSourceFromDB;
    }

    public MutableLiveData<PageKeyedDataSource<Integer, PhotoDetails>> getItemLiveDataSourceFromDB() {
        return itemLiveDataSource;
    }
}


