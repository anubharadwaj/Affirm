package com.example.anubharadwaj.myapplication.fragments;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.example.anubharadwaj.myapplication.R;
import com.example.anubharadwaj.myapplication.utils.Constants;
import butterknife.BindView;
import butterknife.ButterKnife;



public class FlickrPhotoDetailsFragment extends Fragment {

    String url;
    @BindView(R.id.ivPhotoDetails)
    ImageView ivPhotoDetails;
    @BindView(R.id.progressBarForDetails)
    ProgressBar progressBar;

    public FlickrPhotoDetailsFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_flickr_photo_details, container, false);

        ButterKnife.bind(this, view);
       if (this.getArguments() != null) {
            url = getArguments().getString(Constants.PHOTO_URL_KEY);
        }


        if(url!=null || !url.isEmpty()){
            progressBar.setVisibility(View.VISIBLE);
            Glide.with(getActivity())
                    .load(url)
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, com.bumptech.glide.request.target.Target<Drawable> target, boolean isFirstResource) {
                            progressBar.setVisibility(View.GONE);
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, com.bumptech.glide.request.target.Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            progressBar.setVisibility(View.GONE);
                            return false;
                        }
                    }).into(ivPhotoDetails);
        }
        return  view;
    }
}
