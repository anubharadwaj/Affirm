package com.example.anubharadwaj.myapplication.api;

import com.example.anubharadwaj.myapplication.database.entity.FlickrPhotoResponse;
import com.example.anubharadwaj.myapplication.utils.Constants;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FlickrWebService {

    @GET(Constants.END_POINT)
    Call<FlickrPhotoResponse> fetchPhotos(@Query("text") String text, @Query("extras") String extras,
                                          @Query("format") String format, @Query("nojsoncallback") String nojsoncallback,
                                          @Query("page") int pageNumber,
                                          @Query("perpage") int perPage);
}
