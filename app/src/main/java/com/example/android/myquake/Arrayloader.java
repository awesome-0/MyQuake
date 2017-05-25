package com.example.android.myquake;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.ArrayList;

/**
 * Created by Samuel on 22/05/2017.
 */

public class Arrayloader extends AsyncTaskLoader<ArrayList<word>> {
    public Arrayloader(Context context) {
        super(context);
    }

    @Override
    public ArrayList<word> loadInBackground() {
        ArrayList<word> d = Fesh.fesh();
        return d;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }


    @Override
    public void deliverResult(ArrayList<word> data) {
        super.deliverResult(data);
    }
}
