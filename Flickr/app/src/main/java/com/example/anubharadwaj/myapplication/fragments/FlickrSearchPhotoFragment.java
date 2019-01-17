package com.example.anubharadwaj.myapplication.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.example.anubharadwaj.myapplication.R;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class FlickrSearchPhotoFragment extends Fragment  {
    @BindView(R.id.svSearch)
    SearchView searchView;

    public FlickrSearchPhotoFragment() {
        // Required empty public constructor
    }

    private OnSearchQueryEntered listener;

    public interface OnSearchQueryEntered {
        void onQueryEntered(String query);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_flickr_search_photo, container, false);

        init(view);
        resetSearchView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                listener.onQueryEntered(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnSearchQueryEntered) {
            listener = (OnSearchQueryEntered) context;
        } else {
            throw new ClassCastException(context.toString()
                    + " must implement MyListFragment.OnItemSelectedListener");
        }
    }

    void init(View view){
        searchView=(SearchView) view.findViewById(R.id.svSearch);
        searchView.setQuery("", false);
        searchView.setIconified(true);
        searchView.setVisibility(View.VISIBLE);
        searchView.setIconified(true);
        searchView.requestFocus();
        searchView = (SearchView) view.findViewById(R.id.svSearch);
    }

    public void OnSearchQueryEntered(View v, String query) {
        listener.onQueryEntered(query);
    }

    void resetSearchView(){
        searchView.setQuery("", false);
        searchView.setIconified(true);
        searchView.setVisibility(View.VISIBLE);
        searchView.setIconified(true);
        searchView.requestFocus();
    }

}
