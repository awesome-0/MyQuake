//package com.example.android.myquake;
//
//import android.os.AsyncTask;
//import android.util.Log;
//import android.widget.ListAdapter;
//import android.widget.ListView;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.util.ArrayList;
//
//import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;
//
///**
// * Created by Samuel on 21/05/2017.
// */
//
// public class myAsyncTask extends AsyncTask<String,String,String> {
//
//    public String jsonresponse = null;
//    @Override
//    protected String doInBackground(String... params) {
//        HttpURLConnection connection= null;
//        InputStream stream = null;
//        BufferedReader reader = null;
//
//        try {
//            URL url = new URL("https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=2012-01-01&endtime=2012-12-01&minmagnitude=6");
//       connection = (HttpURLConnection) url.openConnection();
//            connection.connect();
//
//            stream = connection.getInputStream();
//            reader = new BufferedReader(new InputStreamReader(stream));
//            String line ="";
//            StringBuffer buffer = new StringBuffer();
//
//            while((line = reader.readLine())!= null){
//
//                buffer.append(line);
//            }
//             jsonresponse = buffer.toString();
//       return  buffer.toString();
//
//
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }finally {
//            if(connection != null){connection.disconnect();}
//            try {
//                if(stream != null){stream.close();}
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return null;
//
//
//    }
//
//    @Override
//    protected void onPostExecute(String s) {
//
//      //  super.onPostExecute(s);
//        ArrayList<word> myword = new ArrayList<>();
//        myword = extractEarthquakes(s);
//
//
//    }
//
//    public static ArrayList<word> extractEarthquakes(String response) {
//
//        // Create an empty ArrayList that we can start adding earthquakes to
//        ArrayList<word> words = new ArrayList<>();
//
//        // Try to parse the SAMPLE_JSON_RESPONSE. If there's a problem with the way the JSON
//        // is formatted, a JSONException exception object will be thrown.
//        // Catch the exception so the app doesn't crash, and print the error message to the logs.
//        try {
//
//            // TODO: Parse the response given by the SAMPLE_JSON_RESPONSE string and
//            // build up a list of Earthquake objects with the corresponding data.
//
//            JSONObject rootobject = new JSONObject(response);
//
//            JSONArray rootarray = rootobject.getJSONArray("features");
//            for(int i = 0;i<rootarray.length();i++){
//
//                JSONObject current = rootarray.getJSONObject(i);
//                JSONObject properties = current.getJSONObject("properties");
//                double magnitude = properties.getDouble("mag");
//                String place = properties.getString("place");
//                long date = properties.getLong("time");
//
//                word earthquake = new word(magnitude,place,date);
//                words.add(earthquake);
//
//            }
//
//
//
//        } catch (JSONException e) {
//            // If an error is thrown when executing any of the above statements in the "try" block,
//            // catch the exception here, so the app doesn't crash. Print a log message
//            // with the message from the exception.
//            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
//        }
//
//        // Return the list of earthquakes
//        getword(words);
//        return words;
//
//
//    }
//
//public static ArrayList<word> getword(ArrayList<word> s){
//    return s;
//}
//
//
//}
