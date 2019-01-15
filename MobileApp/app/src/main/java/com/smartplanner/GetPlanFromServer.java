package com.smartplanner;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetPlanFromServer extends AsyncTask<String, String, String> {
    private OnJsonReceived onJsonReceived;
    private OnPlansReceived onPlansReceived;
    private RequestType requestType;

    GetPlanFromServer(OnJsonReceived onJsonReceived, OnPlansReceived onPlansReceived, RequestType requestType){
        this.onJsonReceived = onJsonReceived;
        this.onPlansReceived = onPlansReceived;
        this.requestType = requestType;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        Log.i("request_plan_result", result);
        if(requestType == RequestType.SINGLE_PLAN)
            onJsonReceived.jsonReceived(result);
        else if(requestType == RequestType.SINGLE_PLAN.ALL_PLANS)
            onPlansReceived.plansReceived(result);
    }
    @Override
    protected String doInBackground(String... strings) {
        String urlString = strings[0];
        String token = strings[1];
        try {
            URL url  = new URL(urlString);
            HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
            httpCon.setRequestMethod("GET");
            httpCon.setRequestProperty("Authorization", "Bearer " + token);
            httpCon.setConnectTimeout(5000);
            httpCon.setReadTimeout(5000);
            BufferedInputStream bis = null;
            if(httpCon.getResponseCode() == HttpURLConnection.HTTP_OK)
                bis = new BufferedInputStream(httpCon.getInputStream());
            else
                bis = new BufferedInputStream(httpCon.getErrorStream());
            ByteArrayOutputStream buf = new ByteArrayOutputStream();
            int result = bis.read();
            while(result != -1) {
                buf.write((byte) result);
                result = bis.read();
            }
            return  buf.toString();
        } catch ( Exception e){
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
