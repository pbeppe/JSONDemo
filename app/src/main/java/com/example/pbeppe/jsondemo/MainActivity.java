package com.example.pbeppe.jsondemo;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public class DownloadTask extends AsyncTask<String,Void,String>
    {


        @Override
        protected String doInBackground(String... strings) {

            String result ="";
            URL url;
            HttpURLConnection urlConnection=null;
            try
            {
                url=new URL(strings[0]);
            }
            catch (MalformedURLException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
