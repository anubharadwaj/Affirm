package com.example.anubharadwaj.myapplication.activities;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import com.example.anubharadwaj.myapplication.R;
import com.example.anubharadwaj.myapplication.fragments.FlickrPhotoDetailsFragment;
import com.example.anubharadwaj.myapplication.utils.Constants;
import butterknife.ButterKnife;

public class FlickrPhotoDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flickr_photo_details);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        showPhotoDetailsFragment(extras.getString(Constants.PHOTO_URL_KEY));
    }

    private void showPhotoDetailsFragment(String url) {
        FlickrPhotoDetailsFragment fragment = new FlickrPhotoDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.PHOTO_URL_KEY, url);
        fragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container_detail, fragment, null)
                .commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
           finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
