package com.example.barcodereader.http;

import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ServerConnection {

    final static String _serverURL = "http://193.122.105.82/query.php";

    public static Bundle requestInfo(String barcode) throws IOException {
        Bundle resultbundle = new Bundle();
        resultbundle.putString("firmName", "init");
        new Thread(){
            public void run() {
                String resultJSONData = getfromserver(barcode);
                Bundle bun = postJSONparse(resultJSONData);

                if(bun.getString("firmName") == "null"){
                    resultbundle.putString("firmName", "No Result");
                    resultbundle.putString("itemName", "");
                    resultbundle.putBundle("firmNews", null);
                }else{
                    resultbundle.putString("firmName", bun.getString("firmName"));
                    resultbundle.putString("itemName", bun.getString("itemName"));
                    resultbundle.putBundle("firmNews", bun.getBundle("firmNews"));
                }
            }
        }.start();
        while(true){
            if(resultbundle.getString("firmName") != "init")
                break;
        }
        return resultbundle;
    }

    private static String getfromserver(String barcode){
        String resultData = null;
        // open connection & send barcode
        URL serverURL = null;
        try {
            serverURL = new URL(_serverURL+"?barcode="+barcode);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HttpURLConnection con = null;
        try {
            con = (HttpURLConnection) serverURL.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }

        con.setReadTimeout(5000);
        con.setConnectTimeout(5000);
        try {
            con.connect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // read string
        try {
            if(con.getResponseCode() == con.HTTP_OK) {
                InputStream response = con.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(response, "UTF-8");
                BufferedReader bf = new BufferedReader(inputStreamReader);

                StringBuffer sb = new StringBuffer();
                String line;
                while ((line = bf.readLine()) != null) {
                    sb.append(line);
                }
                resultData = sb.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d("RESULT DATA", resultData);
        return resultData;
    }

    private static Bundle postJSONparse(String JSONdata){
        Bundle bundle = null;

        try {
            JSONObject jsonObject = new JSONObject((JSONdata));

            bundle = new Bundle();
            bundle.putString("firmName", jsonObject.getString("companyname"));
            bundle.putString("itemName", "Product: "+jsonObject.getString("itemname"));

            JSONArray newsJsonArray = jsonObject.getJSONArray("news");
            JSONObject newsJsonObject;
            Bundle newsBundle = new Bundle();

            for(int i = 0; i < newsJsonArray.length(); i++) {
                newsJsonObject = newsJsonArray.getJSONObject(i);

                String[] newsContents = {newsJsonObject.getString("title"), newsJsonObject.getString("date"), newsJsonObject.getString("link")};

                newsBundle.putStringArray("firmNews"+i, newsContents);
            }

            bundle.putBundle("firmNews", newsBundle);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return bundle;
    }

}
