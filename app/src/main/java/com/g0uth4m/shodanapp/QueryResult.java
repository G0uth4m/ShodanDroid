package com.g0uth4m.shodanapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.AsyncTask;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

public class QueryResult extends AppCompatActivity {

    public static String searchUrl = "https://api.shodan.io/shodan/host/search?key=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_result);

        String query = getIntent().getStringExtra("query");
        String api_key = getIntent().getStringExtra("api_key");


        if (isNetworkConnected()) {
            TextView textView = findViewById(R.id.result_text);
            textView.setText(query);

            ProgressBar progressBar = findViewById(R.id.loading);
            progressBar.setVisibility(View.VISIBLE);

            SearchAsyncTask task = new SearchAsyncTask();
            task.execute(searchUrl + api_key + "&query=" + query);
        } else {
            TextView textView1 = findViewById(R.id.info);
            textView1.setText("No Internet connection");

            LinearLayout linearLayout = findViewById(R.id.result_info);
            linearLayout.setVisibility(View.VISIBLE);
        }
    }

    public void updateUi(ArrayList<ListItem> listItems){
        ListItemAdapter itemsAdapter = new ListItemAdapter(this, listItems);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(itemsAdapter);
    }

    private class SearchAsyncTask extends AsyncTask<String, Void, ArrayList<ListItem>> {

        @Override
        protected ArrayList<ListItem> doInBackground(String... urls) {
            ArrayList<ListItem> listItems = QueryUtils.fetchShadonSearch(urls[0]);
            return listItems;
        }


        @Override
        protected void onPostExecute(ArrayList<ListItem> listItems) {
            ProgressBar progressBar = findViewById(R.id.loading);
            progressBar.setVisibility(View.GONE);
            LinearLayout linearLayout = findViewById(R.id.result_info);

            if (listItems.size() == 0) {
                TextView textView = findViewById(R.id.info);
                textView.setText("No results for query: ");

                linearLayout.setVisibility(View.VISIBLE);
            } else {
                linearLayout.setVisibility(View.VISIBLE);
                updateUi(listItems);
            }
        }
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }
}
