package com.example.todoapp;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class NewActivity extends AppCompatActivity implements View.OnClickListener {

    Button newBtnEntry;
    Button btnToTop;
    EditText titleEditText;
    EditText memoEditText;
    Spinner years ;
    Spinner months ;
    Spinner days ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        titleEditText = findViewById(R.id.title);
        memoEditText = findViewById(R.id.memo);
        newBtnEntry = findViewById(R.id.newBtnEntry);
        btnToTop = findViewById(R.id.btnToTop);

        newBtnEntry.setOnClickListener(this);
        btnToTop.setOnClickListener(this);

        // スピナー部品をレイアウトから取得
        years = findViewById(R.id.years);
        months = findViewById(R.id.months);
        days = findViewById(R.id.days);


    }

    public void onClick(View v) {

        if (v.getId() == R.id.newBtnEntry) {
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