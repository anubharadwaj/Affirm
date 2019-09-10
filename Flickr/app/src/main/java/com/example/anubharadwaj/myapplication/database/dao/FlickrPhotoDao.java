package com.example.anubharadwaj.myapplication.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.example.anubharadwaj.myapplication.database.entity.PhotoDetails;
import java.util.List;
import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface FlickrPhotoDao {

    @Insert(onConflict = REPLACE)
    void save(PhotoDetails photo);

    @Query("SELECT * FROM photodetails")
    LiveData<List<PhotoDetails>> loadPhotos();
}