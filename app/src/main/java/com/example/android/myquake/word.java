package com.example.android.myquake;

/**
 * Created by Samuel on 21/05/2017.
 */

public class word {
    private double magnitude;private String place;private long date;private String url;

    public word(double mmagnitude,String mplace,long mdate,String murl){
        magnitude = mmagnitude;
        place = mplace;
        date=mdate;
        url = murl;
    }
    public double getMagnitude(){
        return  magnitude;
    }
    public String getPlace(){
        return place;
    }
    public long getDate(){
        return date;
    }
    public String getUrl(){return  url;}
}




