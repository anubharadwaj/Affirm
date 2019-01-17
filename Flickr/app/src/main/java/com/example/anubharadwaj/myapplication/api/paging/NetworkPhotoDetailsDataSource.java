package com.example.anubharadwaj.myapplication.api.paging;

import android.arch.paging.PageKeyedDataSource;
import android.support.annotation.NonNull;

import com.example.anubharadwaj.myapplication.api.FlickrRetrofitClient;
import com.example.anubharadwaj.myapplication.database.FlickrPhotoDatabase;
import com.example.anubharadwaj.myapplication.database.dao.FlickrPhotoDao;
import com.example.anubharadwaj.myapplication.database.entity.FlickrPhotoResponse;
import com.example.anubharadwaj.myapplication.database.entity.PhotoDetails;
import com.example.anubharadwaj.myapplication.utils.Constants;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NetworkPhotoDetailsDataSource extends PageKeyedDataSource<Integer,PhotoDetails> {
    public static final int PAGE_SIZE = 100;
    private static final int FIRST_PAGE = 1;
    private FlickrRetrofitClient retrofitClient;
    private final FlickrPhotoDao photoDao;
    private String query;

    public NetworkPhotoDetailsDataSource(String query) {
        FlickrPhotoDatabase flickrPhotoDatabase = FlickrPhotoDatabase.getInstance();
        this.photoDao = flickrPhotoDatabase.photoDao();
        this.query = query;
    }


    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, PhotoDetails> callback) {
        Executor executor = Executors.newSingleThreadExecutor();
        retrofitClient = FlickrRetrofitClient.getInstance();
        retrofitClient.getApi().fetchPhotos(query, Constants.EXTRAS, Constants.FORMAT, Constants.NOJSONCALLBACK,
                FIRST_PAGE, PAGE_SIZE).enqueue(new Callback<FlickrPhotoResponse>() {
            @Override
            public void onResponse(Call<FlickrPhotoResponse> call, Response<FlickrPhotoResponse> response) {
                executor.execute(() -> {
                    if (response.body() != null && response.isSuccessful()) {
                        FlickrPhotoResponse items = response.body();
                        photoDao.save(items.getPhotos().getPhoto());
                        callback.onResult(response.body().getPhotos().getPhoto(), null, items.getPhotos().getPage() + 1);

                    }
                });
            }

            @Override
            public void onFailure(Call<FlickrPhotoResponse> call, Throwable t) {
            }
        });
    }


    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, PhotoDetails> callback) {
        Executor executor = Executors.newSingleThreadExecutor();
        retrofitClient = FlickrRetrofitClient.getInstance();
        retrofitClient.getApi().fetchPhotos(query, Constants.EXTRAS, Constants.FORMAT, Constants.NOJSONCALLBACK,
                params.key, PAGE_SIZE).enqueue(new Callback<FlickrPhotoResponse>() {
            @Override
            public void onResponse(Call<FlickrPhotoResponse> call, Response<FlickrPhotoResponse> response) {
                executor.execute(() -> {
                    if (response.body() != null && response.isSuccessful()) {
                        FlickrPhotoResponse items = response.body();
                        photoDao.save(items.getPhotos().getPhoto());
                        Integer key = (params.key > 1) ? params.key - 1 : null;
                        callback.onResult(response.body().getPhotos().getPhoto(), key);
                    }
                });
            }

            @Override
            public void onFailure(Call<FlickrPhotoResponse> call, Throwable t) {
            }
        });
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, PhotoDetails> callback) {

        Executor executor = Executors.newSingleThreadExecutor();
        retrofitClient = FlickrRetrofitClient.getInstance();
        retrofitClient.getApi().fetchPhotos(query, Constants.EXTRAS, Constants.FORMAT, Constants.NOJSONCALLBACK,
                params.key, PAGE_SIZE).enqueue(new Callback<FlickrPhotoResponse>() {
            @Override
            public void onResponse(Call<FlickrPhotoResponse> call, Response<FlickrPhotoResponse> response) {
                executor.execute(() -> {
                    if (response.body() != null && response.isSuccessful()) {
                        Integer key = (params.key > 1) ? params.key +1 : null;
                        callback.onResult(response.body().getPhotos().getPhoto(), key);

                    }
                });
            }

            @Override
            public void onFailure(Call<FlickrPhotoResponse> call, Throwable t) {
            }
        });



    }
}



