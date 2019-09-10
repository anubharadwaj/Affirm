package com.example.anubharadwaj.myapplication.fragments;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.anubharadwaj.myapplication.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FlickrPhotoSearchFragment extends Fragment {


    public FlickrPhotoSearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_flickr_photo_search, container, false);
    }

}
