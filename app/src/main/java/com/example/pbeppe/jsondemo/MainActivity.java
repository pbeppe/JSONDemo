package com.example.pbeppe.jsondemo;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pbeppe.jsondemo.adapter.AttrazioneAdapter;
import com.example.pbeppe.jsondemo.model.Attrazione;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ArrayList attrazioni;
    AttrazioneAdapter attrazioneAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("Start","Inizio Arriva");

        attrazioni=new ArrayList<Attrazione>();
        ListView listView=(ListView)findViewById(R.id.lst_attrazioni);

        DownloadTask task=new DownloadTask();
        task.execute("http://appacademy.it/book/attrazioni.json");
        Log.d("Start","fine");

        attrazioneAdapter=new AttrazioneAdapter(getApplicationContext(),R.layout.item_attrazione,attrazioni);
        listView.setAdapter(attrazioneAdapter);


    }

    public class DownloadTask extends AsyncTask<String,Void,String>
    {
        @Override
        protected String doInBackground(String... strings) {
           // Log.d("Start","do in background");
            String result ="";
            URL url;
            HttpURLConnection urlConnection=null;
            try
            {
              // Log.d("Start","Sono nel Try");
                url=new URL(strings[0]);
                urlConnection=(HttpURLConnection)url.openConnection();
                InputStream in = urlConnection.getInputStream();
                InputStreamReader reader=new InputStreamReader(in) ;
                 int data=reader.read(); // finche c'è da leggere data diverso da 0
                while (data!=-1)
                {
                    //Log.d("Start","Sono in data");
                    char cur= (char)data;
                    result+=cur;
                    data=reader.read();
                }
                //Log.d("Start","Risultato :");
             //Log.d("Start",result);

                return result ;

            }
            catch (MalformedURLException e) {
             //  Log.d("Start","Sono nel primo Catch");
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
              //  Log.d("Start","Sono nel secondo Catch");

        }
            return null;
        }

        @Override
        protected void onPostExecute(String result)
        {
            super.onPostExecute(result);
            Log.i("JsonDemo",result);
            Toast.makeText(MainActivity.this, result,   Toast.LENGTH_SHORT).show();
            try {
                JSONObject jsonObject=new JSONObject(result);
                String attrazioni_str=jsonObject.getString("attractions");
                JSONArray jsonArray=new JSONArray(attrazioni_str);
                for(int i=0;i<jsonArray.length();i++)
                {
                    Attrazione att=new Attrazione();
                    JSONObject jsonOpart= jsonArray.getJSONObject(i);
                    String nome=jsonOpart.getString("name");
                    String tipo=jsonOpart.getString("type");
                    String latitudine=jsonOpart.getString("latitude");
                    String longitudine=jsonOpart.getString("longitude");
                    att.setName(nome);
                    att.setType(tipo);
                    attrazioni.add(att);

                    String is=String.valueOf(i);
                    Log.d("Riga",is+" "+nome +" "+tipo+" "+ longitudine+" "+latitudine);
                //    TextView Tex=(TextView)findViewById(R.id.id_Text);
                  //  Tex.setText(nome);

                }

                ;
                Log.d ("Jsondemo",attrazioni_str);
            } catch (JSONException e) {
                e.printStackTrace();
                Log.d("Eccezione","Json");
            }
            attrazioneAdapter.notifyDataSetChanged();
        }
    }
}
