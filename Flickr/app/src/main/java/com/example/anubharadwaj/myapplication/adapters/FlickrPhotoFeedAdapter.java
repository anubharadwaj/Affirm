package com.example.anubharadwaj.myapplication.adapters;

import android.arch.paging.PagedListAdapter;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
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
import com.example.anubharadwaj.myapplication.database.entity.PhotoDetails;



public class FlickrPhotoFeedAdapter extends PagedListAdapter<PhotoDetails,FlickrPhotoFeedAdapter.FlickrPhotoFeedHolder> {
    private Context context;
    private ClickListener clickListener;

    public FlickrPhotoFeedAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<PhotoDetails> DIFF_CALLBACK = new DiffUtil.ItemCallback<PhotoDetails>() {
        @Override
        public boolean areItemsTheSame(PhotoDetails oldItem, PhotoDetails newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(PhotoDetails oldItem, PhotoDetails newItem) {
            return oldItem.getTitle().equals(newItem.getTitle()) &&
                    oldItem.getUrlS().equals(newItem.getUrlS());
        }
    };

    public void setListener(ClickListener clickListener){
        this.clickListener = clickListener;
    }


    @NonNull
    @Override
    public FlickrPhotoFeedHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.photo_item_details, parent, false);
        return new FlickrPhotoFeedHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FlickrPhotoFeedHolder holder, int position) {
        PhotoDetails photo = getItem(position);
        if (photo != null) {

            holder.pbLoading.setVisibility(View.VISIBLE);
            Glide.with(context)
                    .load(photo.getUrlS())
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, com.bumptech.glide.request.target.Target<Drawable> target, boolean isFirstResource) {
                            holder.pbLoading.setVisibility(View.GONE);
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, com.bumptech.glide.request.target.Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            holder.pbLoading.setVisibility(View.GONE);
                            return false;
                        }
                    }).into(holder.ivPhoto);
        }
    }


    class FlickrPhotoFeedHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageView ivPhoto;
        private ProgressBar pbLoading;


        public FlickrPhotoFeedHolder(View itemView) {
            super(itemView);
            ivPhoto = itemView.findViewById(R.id.ivPhoto);
            pbLoading = itemView.findViewById(R.id.pbLoading);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            if(clickListener != null){
                clickListener.itemClicked(itemView, getAdapterPosition());
            }
        }
    }

    public interface ClickListener{
        void itemClicked(View view, int position);
    }
}
