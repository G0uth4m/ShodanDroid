package com.g0uth4m.shodanapp;

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
import java.nio.charset.Charset;
import java.util.ArrayList;

public class QueryUtils {

    public static final String LOG_TAG = QueryUtils.class.getSimpleName();


    private QueryUtils() {
    }

    private static URL createUrl(String stringUrl){
        URL url = null;
        try{
            url = new URL(stringUrl);
        } catch (MalformedURLException e){

        }
        return url;
    }

    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        if(url == null){
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;

        try{
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            if(urlConnection.getResponseCode() == 200){
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            }
            else{

            }
        } catch (IOException e){

        } finally {
            if (urlConnection != null){
                urlConnection.disconnect();
            }
            if(inputStream != null){
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    private static String readFromStream(InputStream inputStream) throws IOException{
        StringBuilder output = new StringBuilder();
        if(inputStream != null){
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null){
                output.append(line);
                line = reader.readLine();
            }
        }
        return  output.toString();
    }

    public static ArrayList<ListItem> fetchShadonSearch(String requestUrl){
        URL url = createUrl(requestUrl);

        String jsonResponse = null;
        try{
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e){

        }

        ArrayList<ListItem> quakes = extractShadonSearch(jsonResponse);
        return quakes;
    }

    public static ArrayList<ListItem> extractShadonSearch(String SAMPLE_JSON_RESPONSE) {

        ArrayList<ListItem> searchResults = new ArrayList<>();

        try {
            JSONObject root = new JSONObject(SAMPLE_JSON_RESPONSE);
            JSONArray matchesArray = root.getJSONArray("matches");

            for(int i = 0; i < matchesArray.length(); i++){
                JSONObject currentSearch = matchesArray.getJSONObject(i);
                String ip = currentSearch.getString("ip_str");
                String port = currentSearch.getInt("port") + "";

                JSONObject location = currentSearch.getJSONObject("location");
                String city = location.getString("city");
                String country = location.getString("country_name");

                String isp = currentSearch.getString("isp");
                String org = currentSearch.getString("org");
                String data = currentSearch.getString("data");

                searchResults.add(new ListItem(isp, port, city, country, ip, org, data));

            }

        } catch (JSONException e) {
            Log.e("QueryUtils", "Problem parsing the JSON results", e);
        }
        return searchResults;
    }

}
