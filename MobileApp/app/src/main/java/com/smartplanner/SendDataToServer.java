package com.smartplanner;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class SendDataToServer extends AsyncTask<String, String, String> {
    OnUserCreated onUserCreated;

    public SendDataToServer(OnUserCreated onUserCreated) {
        this.onUserCreated = onUserCreated;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        Log.i("send_data_result", result);
        onUserCreated.onUserCreated(result);
    }

    @Override
    protected String doInBackground(String... strings) {
        String urlString = strings[0];
        String body = strings[1];
        try {

            URL url = new URL(urlString);
            HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
            httpCon.setDoOutput(true);
            httpCon.setRequestMethod("POST");
            httpCon.setConnectTimeout(5000);
            httpCon.setReadTimeout(5000);
            httpCon.addRequestProperty("Content-Type", "application/json");

            OutputStream os = httpCon.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
            osw.write(body);
            osw.flush();
            osw.close();
            os.close();
            BufferedInputStream bis = null;
            if (httpCon.getResponseCode() == HttpURLConnection.HTTP_OK)
                bis = new BufferedInputStream(httpCon.getInputStream());
            else bis = new BufferedInputStream(httpCon.getErrorStream());
            ByteArrayOutputStream buf = new ByteArrayOutputStream();
            int result = bis.read();
            while (result != -1) {
                buf.write((byte) result);
                result = bis.read();
            }
            return buf.toString();
        } catch (Exception ex){
            ex.printStackTrace();
            return ex.getMessage();
        }
    }
}
