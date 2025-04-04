package com.example.todoapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class NewActivity extends AppCompatActivity implements View.OnClickListener {

    EditText title;
    EditText memo;
    Button btnEntry;
    Button btnToTop;
    Spinner years ;
    Spinner months ;
    Spinner days ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        title = findViewById(R.id.title);
        memo = findViewById(R.id.memo);
        btnEntry = findViewById(R.id.btnEntry);
        btnToTop = findViewById(R.id.btnToTop);

        btnEntry.setOnClickListener(this);
        btnToTop.setOnClickListener(this);

        // スピナー部品をレイアウトから取得
        years = findViewById(R.id.years);
        months = findViewById(R.id.months);
        days = findViewById(R.id.days);


    }

    public void onClick(View v) {

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