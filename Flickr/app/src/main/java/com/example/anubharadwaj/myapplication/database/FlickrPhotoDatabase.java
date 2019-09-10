package com.example.anubharadwaj.myapplication.database;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.example.anubharadwaj.myapplication.FlickrApplication;
import com.example.anubharadwaj.myapplication.database.dao.FlickrPhotoDao;
import com.example.anubharadwaj.myapplication.database.entity.PhotoDetails;

@Database(entities = {PhotoDetails.class}, version = 1)
public abstract class FlickrPhotoDatabase extends RoomDatabase {

    private static  FlickrPhotoDatabase instance;
    public abstract FlickrPhotoDao photoDao();

    public static synchronized FlickrPhotoDatabase getInstance(){
        if(instance == null){
            instance = Room.databaseBuilder(FlickrApplication.getContext(), FlickrPhotoDatabase.class, "photo_database").fallbackToDestructiveMigration().build();
        }
        return instance;
    }
}