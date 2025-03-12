package com.example.todoapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Edit extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

    }

    public void onClick(View v) {
        if (v.getId() == R.id.btnEntry || v.getId() == R.id.btnDelete) {
            Intent intent = new Intent(this, List.class);
            startActivity(intent);
        }

    }
}