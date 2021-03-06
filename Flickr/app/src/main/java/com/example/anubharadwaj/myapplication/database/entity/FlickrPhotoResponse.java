package com.example.anubharadwaj.myapplication.database.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FlickrPhotoResponse {
    @SerializedName("photos")
    @Expose
    private Photos photos;

    public Photos getPhotos() {
        return photos;
    }

    public void setPhotos(Photos photos) {
        this.photos = photos;
    }
}