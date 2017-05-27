package com.example.android.myquake;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

//import static com.example.android.myquake.myAsyncTask.getword;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<word>>{

    TextView text;
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

        text = (TextView) findViewById(R.id.texto);

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

        if(data != null) {
            View x = (View) findViewById(R.id.loadingtext);
            x.setVisibility(View.GONE);
            arrange(data);
        }
        else {
            text.setText("No internet Connection");
            text.setVisibility(View.VISIBLE);
            View x =(View) findViewById(R.id.loadingtext);
            x.setVisibility(View.GONE);
        }


    }

    @Override
    public void onLoaderReset(Loader<ArrayList<word>> loader) {

    }


}