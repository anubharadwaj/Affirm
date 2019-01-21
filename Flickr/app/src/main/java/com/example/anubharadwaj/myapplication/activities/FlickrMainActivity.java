package com.example.anubharadwaj.myapplication.activities;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.support.v7.widget.SearchView;
import android.view.View;

import com.example.anubharadwaj.myapplication.R;
import com.example.anubharadwaj.myapplication.fragments.FlickrPhotoDetailsFragment;
import com.example.anubharadwaj.myapplication.fragments.FlickrPhotoFeedFragment;
import com.example.anubharadwaj.myapplication.fragments.FlickrPhotoSearchFragment;
import com.example.anubharadwaj.myapplication.utils.Constants;
import butterknife.ButterKnife;


public class FlickrMainActivity extends AppCompatActivity implements FlickrPhotoFeedFragment.OnItemSelectedListener{
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flickr_main);
        ButterKnife.bind(this);
        showSearchFragment();
    }

    private void showFeedFragment(String query) {
        Bundle bundle = new Bundle();
        bundle.putString(Constants.QUERY_KEY, query);
        FlickrPhotoFeedFragment fragment = new FlickrPhotoFeedFragment();
        fragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, fragment, null)
                .commit();
    }

    private void showSearchFragment() {
        FlickrPhotoSearchFragment fragment = new FlickrPhotoSearchFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, fragment, null)
                .commit();
    }


        @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_image_list, menu);
        searchView = (SearchView)  menu.findItem(R.id.action_search).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
               // progressBar.setVisibility(View.VISIBLE);
                showFeedFragment(query);
                searchView.clearFocus();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return  true;
    }

    @Override
    public void onPhotoSelected(String url) {
        Intent i = new Intent(FlickrMainActivity.this, FlickrPhotoDetailsActivity.class);
        Bundle bundle =new Bundle();
        bundle.putString(Constants.PHOTO_URL_KEY, url);
        i.putExtras(bundle);
        startActivity(i);
    }
}
