package com.example.anubharadwaj.myapplication.fragments;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.paging.PagedList;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.anubharadwaj.myapplication.R;
import com.example.anubharadwaj.myapplication.adapters.FlickrPhotoFeedAdapter;
import com.example.anubharadwaj.myapplication.database.entity.PhotoDetails;
import com.example.anubharadwaj.myapplication.repository.FlickrRepository;
import com.example.anubharadwaj.myapplication.utils.Constants;
import com.example.anubharadwaj.myapplication.view_models.FlickrPhotoFeedViewModel;
import com.example.anubharadwaj.myapplication.view_models.FlickrPhotoFeedViewModelFactory;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class FlickrPhotoFeedFragment extends Fragment implements FlickrPhotoFeedAdapter.ClickListener {
    // FOR DESIGN
    @BindView(R.id.rv)
    RecyclerView rv;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    String url="";
    private OnItemSelectedListener listener;
    public interface OnItemSelectedListener {
         void onPhotoSelected(String url);
    }


    private FlickrPhotoFeedAdapter adapter;
    PagedList<PhotoDetails> photosResult;
    public FlickrPhotoFeedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_flickr_photo_feed, container, false);
        ButterKnife.bind(this, view);
        RecyclerView recyclerView = rv;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        adapter = new FlickrPhotoFeedAdapter();
        recyclerView.setAdapter(adapter);
        adapter.setListener(this);
        if (this.getArguments() != null) {
            getPhotoFeed(getArguments().getString(Constants.QUERY_KEY));
        }
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnItemSelectedListener) {
            listener = (OnItemSelectedListener) context;
        } else {
            throw new ClassCastException(context.toString()
                    + " must implement MyListFragment.OnItemSelectedListener");
        }
    }


    private void getPhotoFeed(String query) {
        FlickrRepository flickrRepository = FlickrRepository.getInstance();
        LiveData<PagedList<PhotoDetails>> photoPagedList = flickrRepository.getPhotos(true, query);
        FlickrPhotoFeedViewModelFactory factory = new FlickrPhotoFeedViewModelFactory(flickrRepository, query);
        FlickrPhotoFeedViewModel viewModel = ViewModelProviders.of(this, factory).get(FlickrPhotoFeedViewModel.class);

        FlickrRepository.getInstance().photoPagedList.observe(this, new Observer<PagedList<PhotoDetails>>() {
            @Override
            public void onChanged(@Nullable PagedList<PhotoDetails> photos) {
                progressBar.setVisibility(View.GONE);
                if (photos != null) {
                    photosResult = photos;
                    adapter.submitList(photos);
                }
            }
        });

    }


    @Override
    public void itemClicked(View view, int position) {
        if(photosResult!=null && !photosResult.isEmpty()){
           url = photosResult.get(position).getUrlS();
            listener.onPhotoSelected(url);
        }
    }

}
