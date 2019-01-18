package com.example.anubharadwaj.myapplication.api.network;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.example.anubharadwaj.myapplication.repository.FlickrRepository;


public class InternetConnectorReceiver  extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        if (isConnected) {
            try {
                Toast.makeText(context, "Network is connected", Toast.LENGTH_LONG).show();
                FlickrRepository.getInstance().getPhotos(true,"");
                // entry point of data
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(context, "Network is disconnected", Toast.LENGTH_LONG).show();
            FlickrRepository.getInstance().getPhotos(false,"");
        }

    }

}
