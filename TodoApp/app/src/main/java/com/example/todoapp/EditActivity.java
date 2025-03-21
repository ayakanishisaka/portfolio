package com.example.todoapp;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class EditActivity extends AppCompatActivity {
    Button btnEntry;
    Button btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        btnEntry = findViewById(R.id.btnEntry);
        btnDelete = findViewById(R.id.btnDelete);
//        btnEntry.setOnClickListener(this);
//        btnDelete.setOnClickListener(this);
    }

    public void onClick(View v) {
        if (v.getId() == R.id.btnEntry || v.getId() == R.id.btnDelete) {
            Intent intent = new Intent(this, ListActivity.class);
            startActivity(intent);
        }

    }
}