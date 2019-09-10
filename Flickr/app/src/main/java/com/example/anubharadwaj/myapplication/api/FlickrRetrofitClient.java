package com.example.anubharadwaj.myapplication.api;

import com.example.anubharadwaj.myapplication.utils.Constants;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FlickrRetrofitClient {
    private static FlickrRetrofitClient mInstance;
        private Retrofit retrofit;

        private FlickrRetrofitClient(){
            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }


        public static synchronized FlickrRetrofitClient getInstance(){
            if(mInstance == null){
                mInstance = new FlickrRetrofitClient();
            }
            return mInstance;
        }

        public FlickrWebService getApi(){
            return retrofit.create(FlickrWebService.class);
        }
}

