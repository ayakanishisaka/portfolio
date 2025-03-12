package com.example.todoapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class New extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

    }

    public void onClick(View v) {
        if (v.getId() == R.id.newBtnEntry) {
            Intent intentEntry = new Intent(this, List.class);
            startActivity(intentEntry);
        }
        if (v.getId() == R.id.btnToTop) {
            Intent intentToTop = new Intent(this, MainActivity.class);
            startActivity(intentToTop);
        }
        if (v.getId() == R.id.btnNew || v.getId() == R.id.newBtnEntry) {
            finish();
        }
    }
}