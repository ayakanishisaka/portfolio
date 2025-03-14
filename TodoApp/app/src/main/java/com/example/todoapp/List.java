package com.example.todoapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class List extends AppCompatActivity implements View.OnClickListener {
    Button newBtnEntry;
    Button btnToTop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        newBtnEntry = findViewById(R.id.btnNew);
        btnToTop = findViewById(R.id.btnToTop);

        btnToTop.setOnClickListener(this);
        newBtnEntry.setOnClickListener(this);
    }

    public void onClick(View v) {
        if (v.getId() == R.id.btnNew) {
            Intent intentBtnNew = new Intent(this, New.class);
            startActivity(intentBtnNew);
        } else if (v.getId() == R.id.btnToTop) {
            Intent intentToTop = new Intent(this, MainActivity.class);
            startActivity(intentToTop);
        } else
            finish();
    }

}