package com.example.todoapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Edit extends AppCompatActivity {
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
            Intent intent = new Intent(this, List.class);
            startActivity(intent);
        }

    }
}