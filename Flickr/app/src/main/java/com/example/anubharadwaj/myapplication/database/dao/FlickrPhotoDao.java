package com.example.anubharadwaj.myapplication.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.anubharadwaj.myapplication.database.entity.PhotoDetails;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface FlickrPhotoDao {

    @Insert(onConflict = REPLACE)
    void save(List<PhotoDetails> photo);

    @Query("SELECT * FROM photodetails")
    public List<PhotoDetails> loadPhotos();
}