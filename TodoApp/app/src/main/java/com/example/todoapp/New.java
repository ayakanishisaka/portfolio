package com.example.todoapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class New extends AppCompatActivity implements View.OnClickListener {

    Button newBtnEntry;
    Button btnToTop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
        newBtnEntry = findViewById(R.id.newBtnEntry);
        btnToTop = findViewById(R.id.btnToTop);

        newBtnEntry.setOnClickListener(this);
        btnToTop.setOnClickListener(this);

    }

    public void onClick(View v) {
        if (v.getId() == R.id.newBtnEntry) {
            Intent intentNewEntry = new Intent(this, List.class);
            startActivity(intentNewEntry);
        }
        if (v.getId() == R.id.btnToTop) {
            Intent intentToTop = new Intent(this, MainActivity.class);
            startActivity(intentToTop);
            finish();
        }
        if (v.getId() == R.id.btnNew) {
            finish();
        }
    }
}