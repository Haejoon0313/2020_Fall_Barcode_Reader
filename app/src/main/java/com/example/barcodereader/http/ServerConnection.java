package com.example.barcodereader.http;

import android.os.Bundle;
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

                if(bun == null){
                    resultbundle.putString("firmName", "결과를 찾을 수 없습니다.");
                    resultbundle.putString("firmNation", "");
                    resultbundle.putString("firmInfo", "");
                }else{
                    resultbundle.putString("firmName", bun.getString("firmName"));
                    resultbundle.putString("firmNation", "국가: 대한민국");
                    resultbundle.putString("firmInfo", bun.getString("firmInfo"));
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
        Log.d("resultData", resultData);
        return resultData;
    }

    private static Bundle postJSONparse(String JSONdata){
        Bundle bundle = null;

        try {
            JSONObject jsonBigObject = new JSONObject(JSONdata);
            JSONArray jsonArray = new JSONArray(jsonBigObject.getString("Item"));
            try{
                JSONObject jsonObject = jsonArray.getJSONObject(0);
                bundle = new Bundle();
                bundle.putString("firmName", jsonObject.getString("COMPANYNAME"));
                bundle.putString("firmInfo", "제품명: "+jsonObject.getString("ITEMNAME")+"\n\n기업 정보 추가 예정");
            }catch (JSONException e){
                e.printStackTrace();
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return bundle;
    }

}
