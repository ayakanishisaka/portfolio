package com.example.test;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class NewActivity extends AppCompatActivity implements View.OnClickListener {

    EditText title;
    EditText memo;
    Button btnList;
    Button btnToTop;
    TodoDbHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        title = findViewById(R.id.title);
        memo = findViewById(R.id.memo);
        btnList = findViewById(R.id.btnList);
        btnToTop = findViewById(R.id.btnToTop);

        btnList.setOnClickListener(this);
        btnToTop.setOnClickListener(this);

        // スピナーに年、月、日を設定するメソッド
        Spinner yearSpinner = findViewById(R.id.years);
        Spinner monthSpinner = findViewById(R.id.months);
        Spinner daySpinner = findViewById(R.id.days);

        // 年を選択
        ArrayList<String> years = new ArrayList<>();
        for (int i = 2025; i <= 2035; i++) {
            years.add(String.valueOf(i));
        }

        // 月を選択
        ArrayList<String> months = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            months.add(String.format("%02d", i));
        }

        // 日を選択
        ArrayList<String> days = new ArrayList<>();
        for (int i = 1; i <= 31; i++) {
            days.add(String.format("%02d", i));
        }

        // アダプターを使ってスピナーに選択肢を設定
        ArrayAdapter<String> yearAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, years);
        yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yearSpinner.setAdapter(yearAdapter);

        ArrayAdapter<String> monthAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, months);
        monthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        monthSpinner.setAdapter(monthAdapter);

        ArrayAdapter<String> dayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, days);
        dayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        daySpinner.setAdapter(dayAdapter);



    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnList) {
            Toast.makeText(this, "登録されました", Toast.LENGTH_SHORT).show();
            Intent intentList = new Intent(this, ToDoListActivity.class);
            startActivity(intentList);
        }
        if (v.getId() == R.id.btnToTop) {
            Intent intentToTop = new Intent(this, MainActivity.class);
            startActivity(intentToTop);
        }
    }
}