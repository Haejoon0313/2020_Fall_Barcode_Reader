package com.example.barcodereader;

import android.content.Intent;

import androidx.fragment.app.Fragment;

import com.example.barcodereader.ui.home.HomeFragment;
import com.example.barcodereader.ui.result.ResultFragment;

import java.net.MalformedURLException;
import java.net.URL;

public class ServerConnection {

    class Constvar {
        final static String _serverIP = "http://127.0.0.1";
    }

    public static void requestInfo(String barcode) {
        // create URL
        try {
            URL serverURL = new URL(Constvar._serverIP);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        // send info

        // listen info

        // print info
        
    }

}
