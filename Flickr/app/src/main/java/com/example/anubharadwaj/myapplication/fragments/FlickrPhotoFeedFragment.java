package com.example.anubharadwaj.myapplication.fragments;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.paging.PagedList;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.anubharadwaj.myapplication.R;
import com.example.anubharadwaj.myapplication.adapters.FlickrPhotoFeedAdapter;
import com.example.anubharadwaj.myapplication.database.entity.PhotoDetails;
import com.example.anubharadwaj.myapplication.view_models.FlickrPhotoFeedViewModel;
import com.example.anubharadwaj.myapplication.view_models.FlickrPhotoFeedViewModelFactory;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class FlickrPhotoFeedFragment extends Fragment {


    private FlickrPhotoFeedViewModel viewModel;
    private FlickrPhotoFeedAdapter adapter;
    String query="";

    // FOR DESIGN
    @BindView(R.id.rv)
    RecyclerView rv;


    public FlickrPhotoFeedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_flickr_photo_feed, container, false);
        ButterKnife.bind(this, view);

        query = this.getArguments().getString("query");

        RecyclerView recyclerView = rv;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        adapter = new FlickrPhotoFeedAdapter();
        recyclerView.setAdapter(adapter);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.configureViewModel();

    }

    private void configureViewModel(){
       // viewModel = ViewModelProviders.of(this).get(FlickrPhotoFeedViewModel.class);
        viewModel = ViewModelProviders.of(this, new FlickrPhotoFeedViewModelFactory(this, query)).get(FlickrPhotoFeedViewModel.class);
        //listen to data changes and pass it to adapter for displaying in recycler view

        viewModel.photoPagedList.observe(this, new Observer<PagedList<PhotoDetails>>() {
            @Override
            public void onChanged(@Nullable PagedList<PhotoDetails> photos) {
                adapter.submitList(photos);
            }
        });

    }

}