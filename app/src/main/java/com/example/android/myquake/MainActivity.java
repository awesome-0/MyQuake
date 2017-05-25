package com.example.android.myquake;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.graphics.drawable.GradientDrawable;
import android.widget.RelativeLayout;
import android.widget.TextView;

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
import java.util.ArrayList;
import java.util.List;

import static android.R.attr.x;
import static android.R.interpolator.linear;

//import static com.example.android.myquake.myAsyncTask.getword;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<word>>{

   // public static final String LOG_TAG = EarthquakeActivity.class.getName();
private ListAdapter adapter;
    private  ListView earthquakeListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//new myAsyncTask().execute();

LoaderManager lm = getLoaderManager();
        lm.initLoader(1,null,this);
    }


    public  void arrange(ArrayList<word> tree) {
        // Find a reference to the {@link ListView} in the layout

        earthquakeListView = (ListView) findViewById(R.id.list);
        RelativeLayout rn = (RelativeLayout) findViewById(R.id.relative);

        // Create a new {@link ArrayAdapter} of earthquakes

        adapter = new EarthquakeAdapter(this, tree);
        View x =(View) findViewById(R.id.loadingtext);
//

        earthquakeListView.setAdapter(adapter);

        earthquakeListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                        word words = (word)adapterView.getItemAtPosition(position);

                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(words.getUrl()));
                        startActivity(i);

                    }
                }
        );
    }

    @Override
    public Loader<ArrayList<word>> onCreateLoader(int id, Bundle args) {

        TextView text = (TextView) findViewById(R.id.texto);

        if(isOnline())
        { return new Arrayloader(MainActivity.this);
        }
        else{
            text.setText("No internet Connection");
            text.setVisibility(View.VISIBLE);
            View x =(View) findViewById(R.id.loadingtext);
            x.setVisibility(View.GONE);

            return null;
        }

    }
    public boolean isOnline() {
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }
    @Override
    public void onLoadFinished(Loader<ArrayList<word>> loader, ArrayList<word> data) {


        View x =(View) findViewById(R.id.loadingtext);
        x.setVisibility(View.GONE);
        arrange(data);


    }

    @Override
    public void onLoaderReset(Loader<ArrayList<word>> loader) {

    }


}