package com.example.test;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnNew, btnList;
    CalendarView calendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calendarView = findViewById(R.id.calendarView);
        calendarView.setFirstDayOfWeek(Calendar.MONDAY);
        calendarView.setOnDateChangeListener(new MyDateChangeListener());

        btnNew = findViewById(R.id.btnNew);
        btnList = findViewById(R.id.btnList);

        btnNew.setOnClickListener(this);
        btnList.setOnClickListener(this);
    }

    // カレンダーの日付を検知する
    private class MyDateChangeListener implements CalendarView.OnDateChangeListener{
        public void onSelectedDayChange(CalendarView view , int year ,int month , int dayOfMonth){
            Intent intentDate = new Intent(MainActivity.this, NewActivity.class);
            intentDate.putExtra("year",String.valueOf(year));
            intentDate.putExtra("month",String.format("%02d", month+1));
            intentDate.putExtra("day",String.format("%02d",dayOfMonth));
            startActivity(intentDate);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnNew) {
            Intent intentNew = new Intent(this, NewActivity.class);
            startActivity(intentNew);
        }
        if (v.getId() == R.id.btnList) {
            Intent intentList = new Intent(this, ToDoListActivity.class);
            startActivity(intentList);
        }
        if (v.getId() == R.id.calendarView) {
            Intent intentDate = new Intent(this, NewActivity.class);
            startActivity(intentDate);
        }
    }
}
