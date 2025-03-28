package com.example.todoapp;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnNew;
    Button btnList;
    CalendarView calendar;
    private Spinner yearSpinner;
    private Spinner monthSpinner;
    private Spinner daySpinner;
    private DateSpinner dateSpinner;  // DateSpinner クラスのインスタンス


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnNew = findViewById(R.id.btnNew);
        btnList = findViewById(R.id.btnList);

        btnNew.setOnClickListener(this);
        btnList.setOnClickListener(this);

        calendar = findViewById(R.id.calendar);
        Date d = new Date();
// 現在日付を設定
        calendar.setDate(d.getTime());
// 現在日付を取得
        Date today = new Date(calendar.getDate());

        calendar.setFirstDayOfWeek((Calendar.MONDAY));
        int firstDayOfWeek = calendar.getFirstDayOfWeek();

//           calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
//            @Override
//            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
//                // 日付が選択されたときの処理
//                // year, month, dayOfMonth を使用して処理を行う
//                // 予定のTodoリストとかにしてもいいんじゃない？
//            }
//        });
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnNew) {
            Intent intentNew = new Intent(this, NewActivity.class);
            startActivity(intentNew);
        }
        if (v.getId() == R.id.btnList) {
            Intent intentList = new Intent(this, ListActivity.class);
            startActivity(intentList);
        }
        if (v.getId() == R.id.btnToTop) {
            finish();
        }
    }
}