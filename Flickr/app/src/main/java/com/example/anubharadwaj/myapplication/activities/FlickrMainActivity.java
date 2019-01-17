package com.example.anubharadwaj.myapplication.activities;


import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.anubharadwaj.myapplication.R;
import com.example.anubharadwaj.myapplication.fragments.FlickrPhotoFeedFragment;
import com.example.anubharadwaj.myapplication.fragments.FlickrSearchPhotoFragment;


public class FlickrMainActivity extends AppCompatActivity implements FlickrSearchPhotoFragment.OnSearchQueryEntered {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flickr_main);
        showSearchFragment();
    }


    private void showSearchFragment() {
        FlickrSearchPhotoFragment fragment = new FlickrSearchPhotoFragment();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, fragment, null)
                .commit();

    }

    private void showFeedFragment(String query){
            ActionBar actionBar = getSupportActionBar();
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);

            Bundle bundle = new Bundle();
            bundle.putString("query", query);
            FlickrPhotoFeedFragment fragment = new FlickrPhotoFeedFragment();
            fragment.setArguments(bundle);

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, fragment, null)
                    .commit();
    }

    @Override
    public void onBackPressed() {
        showSearchFragment();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            showSearchFragment();
        }
        return super.onOptionsItemSelected(item);
    }



    @Override
    public void onQueryEntered(String query) {
        showFeedFragment(query);
    }
}
