package com.example.pbeppe.jsondemo;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("Start","Inizio Arriva");
        DownloadTask task=new DownloadTask();
        task.execute("http://appacademy.it/book/attrazioni.json");

    }

    public class DownloadTask extends AsyncTask<String,Void,String>
    {


        @Override
        protected String doInBackground(String... strings) {
            Log.d("Start","do in background");
            String result ="";
            URL url;
            HttpURLConnection urlConnection=null;
            try
            {
                Log.d("Start","Sono nel Try");
                url=new URL(strings[0]);
                urlConnection=(HttpURLConnection)url.openConnection();
                InputStream in = urlConnection.getInputStream();
                InputStreamReader reader=new InputStreamReader(in) ;
                 int data=reader.read(); // finche c'è da leggere data diverso da 0
                while (data!=-1)
                {
                    //Log.d("Start","Sono in data");
                    char cur= (char)data;
                    result+=result;
                    data=reader.read();
                }
                Log.d("Start","Risultato :");
               Log.d("Start",result);
                return result ;

            }
            catch (MalformedURLException e) {
               Log.d("Start","Sono nel primo Catch");
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
                Log.d("Start","Sono nel secondo Catch");

            }
            return null;
        }

        @Override
        protected void onPostExecute(String result)
        {
            super.onPostExecute(result);
            Log.i("JsonDemo",result);
        }
    }
}
