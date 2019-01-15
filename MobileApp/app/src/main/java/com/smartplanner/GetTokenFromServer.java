package com.smartplanner;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetTokenFromServer extends AsyncTask<String, String, String> {
    private String body;
    private OnJsonReceived onJsonReceived;

    GetTokenFromServer(OnJsonReceived onJsonReceived, String body){
        this.onJsonReceived = onJsonReceived;
        this.body = body;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        Log.i("request_token_result", result);
        onJsonReceived.jsonReceived(result);
    }
    @Override
    protected String doInBackground(String... strings) {
        String urlString = strings[0];
        OutputStream outputStream = null;
        try {

            URL url  = new URL(urlString);
            HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
            httpCon.setDoOutput(true);
            httpCon.setRequestMethod("POST");
            httpCon.setConnectTimeout(5000);
            httpCon.setReadTimeout(5000);
            httpCon.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            OutputStreamWriter osw = new OutputStreamWriter(httpCon.getOutputStream() ,"UTF-8");
            osw.write(body);
            osw.close();
            httpCon.connect();
            BufferedInputStream bis;
            if(httpCon.getResponseCode() == HttpURLConnection.HTTP_OK)
                bis = new BufferedInputStream(httpCon.getInputStream());
            else
                bis = new BufferedInputStream(httpCon.getErrorStream());
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            int result = bis.read();
            while (result != -1) {
                buffer.write((byte) result);
                result = bis.read();
            }
            return buffer.toString();
        } catch ( Exception e){
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
