package com.example.android.myquake;

/**
 * Created by Samuel on 21/05/2017.
 */

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.zip.Inflater;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.R.attr.format;
import static android.R.attr.resource;
import static com.example.android.myquake.R.id.date;

/**
 * Created by Samuel on 17/05/2017.
 */

public class EarthquakeAdapter extends ArrayAdapter<word> {

    public EarthquakeAdapter(Context context, ArrayList<word>words) {
        super(context,R.layout.arrange, words);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.arrange,parent,false);

        word words = getItem(position);
        TextView magnitude = (TextView)view.findViewById(R.id.magnitude);
        TextView place = (TextView)view.findViewById(R.id.place);
        TextView surrounding = (TextView)view.findViewById(R.id.surrounding);
        TextView date =(TextView)view.findViewById(R.id.date);
        TextView time = (TextView) view.findViewById(R.id.time);
        GradientDrawable magnitudecircle = (GradientDrawable) magnitude.getBackground();
        int magnitudeColor = getmagnitudecolor(words.getMagnitude());

        magnitudecircle.setColor(magnitudeColor);

        String fmag = formatted(words.getMagnitude());
        magnitude.setText(fmag);
        String location = words.getPlace();


       // String [] parts = location.split("f");
        place.setText(words.getPlace());


       // place.setText(parts[1]+ "");
        Date dateobject = new Date(words.getDate());
//       SimpleDateFormat formatteddate = new SimpleDateFormat("MM, DD, yyyy");
//        String dateToDisplay = formatteddate.format(dateobject);
        String fdate = DateFormat.getDateInstance().format(dateobject);
        date.setText(fdate);

        String ftime = DateFormat.getTimeInstance().format(dateobject);
        time.setText(ftime);
        return view;




    }
    public String formatted (double magnitude){
        DecimalFormat df = new DecimalFormat("0.0");
        return  df.format(magnitude);


    }
    public int getmagnitudecolor(double magnitude){

        int magnitudeResourceId ;
        int magfloor = (int) Math.floor(magnitude);

        switch (magfloor){

            case 0:
            case 1:
                magnitudeResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeResourceId = R.color.magnitude9;
                break;
            default :
                magnitudeResourceId = R.color.magnitude10plus;
                break;

        }
        return ContextCompat.getColor(getContext(),magnitudeResourceId);

    }
}
