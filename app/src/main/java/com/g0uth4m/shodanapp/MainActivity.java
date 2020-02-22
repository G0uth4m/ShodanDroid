package com.g0uth4m.shodanapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void shodan_query(View view) {
        TextInputEditText textInputEditText = findViewById(R.id.query);
        String query = textInputEditText.getText().toString();

        TextInputEditText textInputEditText1 = findViewById(R.id.api_key);
        String api_key = textInputEditText1.getText().toString();

        if (!query.isEmpty() && !api_key.isEmpty()) {
            Intent intent = new Intent(MainActivity.this, QueryResult.class);
            intent.putExtra("query", query);
            intent.putExtra("api_key", api_key);
            startActivity(intent);
        }
    }
}
