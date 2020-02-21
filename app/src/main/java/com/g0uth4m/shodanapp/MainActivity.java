package com.g0uth4m.shodanapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void shodan_query(View view) {
        TextInputEditText textInputEditText = findViewById(R.id.query);
        String query = textInputEditText.getText().toString();

        if (!query.isEmpty()) {
            Intent intent = new Intent(MainActivity.this, QueryResult.class);
            intent.putExtra("query", query);
            startActivity(intent);
        }
    }
}
