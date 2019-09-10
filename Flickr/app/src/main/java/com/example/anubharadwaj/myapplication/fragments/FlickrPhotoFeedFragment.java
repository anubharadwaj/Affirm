package com.example.anubharadwaj.myapplication.fragments;


import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import com.example.anubharadwaj.myapplication.R;
import com.example.anubharadwaj.myapplication.adapters.FlickrPhotoFeedAdapter;
import com.example.anubharadwaj.myapplication.database.entity.PhotoDetails;
import com.example.anubharadwaj.myapplication.utils.Constants;
import com.example.anubharadwaj.myapplication.view_models.FlickrPhotoFeedViewModel;
import com.example.anubharadwaj.myapplication.view_models.FlickrPhotoFeedViewModelFactory;
import butterknife.BindView;
import butterknife.ButterKnife;

public class FlickrPhotoFeedFragment extends Fragment implements FlickrPhotoFeedAdapter.ClickListener {

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

        FlickrPhotoFeedViewModelFactory factory = new FlickrPhotoFeedViewModelFactory(getArguments().getString(Constants.QUERY_KEY));
        FlickrPhotoFeedViewModel photoDetailsViewModel = ViewModelProviders.of(this, factory).get(FlickrPhotoFeedViewModel.class);
        photoDetailsViewModel.loadPhotos().observe(this, new Observer<PagedList<PhotoDetails>>() {
            @Override
            public void onChanged(@Nullable PagedList<PhotoDetails> photos) {
                if (photos != null) {
                    photosResult = photos;
                    adapter.submitList(photos);
                }
            }
        });

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


    @Override
    public void itemClicked(View view, int position) {
        if(photosResult!=null && !photosResult.isEmpty()){
           url = photosResult.get(position).getUrlS();
            listener.onPhotoSelected(url);
        }
    }
}
