package com.example.android.myquake;

import android.util.Log;

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

/**
 * Created by Samuel on 22/05/2017.
 */

public class Fesh {

    public static ArrayList<word> fesh (){


        // Create an empty ArrayList that we can start adding earthquakes to
        ArrayList<word> words = new ArrayList<>();

        // Try to parse the SAMPLE_JSON_RESPONSE. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.

        final String RESPONSE = feshjson();
        try {

            // TODO: Parse the response given by the SAMPLE_JSON_RESPONSE string and
            // build up a list of Earthquake objects with the corresponding data.

            JSONObject rootobject = new JSONObject(RESPONSE);

            JSONArray rootarray = rootobject.getJSONArray("features");
            for(int i = 0;i<rootarray.length();i++){

                JSONObject current = rootarray.getJSONObject(i);
                JSONObject properties = current.getJSONObject("properties");
                double magnitude = properties.getDouble("mag");
                String place = properties.getString("place");
                long date = properties.getLong("time");
                String url = properties.getString("url");

                word earthquake = new word(magnitude,place,date,url);
                words.add(earthquake);

            }



        } catch (JSONException | NullPointerException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
            return null;
        }

        // Return the list of earthquakes
        // getword(words);
        return words;


    }




    public static String feshjson(){

        HttpURLConnection connection = null;
        InputStream stream = null;
        BufferedReader reader = null;

        try {
            URL url = new URL("https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=2012-01-01&endtime=2012-12-01&minmagnitude=6");
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();

            stream = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(stream));
            String line = "";
            StringBuffer buffer = new StringBuffer();

            while ((line = reader.readLine()) != null) {

                buffer.append(line);
            }

            return  buffer.toString();


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            try {
                if (stream != null) {
                    stream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;



    }

}
