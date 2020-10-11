package com.example.barcodereader.http;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.example.barcodereader.ui.home.HomeFragment;

import java.net.MalformedURLException;
import java.net.URL;

public class ServerConnection {

    final static String _serverURL = "http://127.0.0.1";

    public static Bundle requestInfo(String barcode) {
        Bundle bundle = new Bundle();

        // create URL
        try {
            URL serverURL = new URL(_serverURL);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        // send info

        // listen info
        bundle.putString("firmName", barcode);

        // return info
        return bundle;
    }

}
