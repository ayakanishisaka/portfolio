package com.example.todoapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnNew;
    Button btnList;
    CalendarView c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnNew = findViewById(R.id.btnNew);
        btnList = findViewById(R.id.btnList);

        btnNew.setOnClickListener(this);
        btnList.setOnClickListener(this);

        c = findViewById(R.id.calendar);
        Date d = new Date();
// 現在日付を設定
        c.setDate(d.getTime());
// 現在日付を取得
        Date today = new Date(c.getDate());

        c.setFirstDayOfWeek((Calendar.MONDAY));
        int firstDayOfWeek = c.getFirstDayOfWeek();
        CalendarView calendarView = findViewById(R.id.calendar);
        TextView selectDay = findViewById(R.id.selectDay);  // 日付が表示されるTextView

//// 日付選択時のリスナーを設定
//        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
//            @Override
//            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
//                // 選択された日付を表示
//                String selectedDate = year + "/" + (month + 1) + "/" + dayOfMonth;
//                selectDay.setText(selectedDate);
//
//                // 選ばれた日付に色を変更
//                selectDay.setTextColor(Color.parseColor("#FF6347"));  // 例: 選ばれた日はトマト色
//
//                // 今日の日付の色を変える
//                changeTodayColor(calendarView);
//            }
//        });
//
//// 今日の日付の色を変更するメソッド
//        private void changeTodayColor(CalendarView calendarView) {
//            Calendar calendar = Calendar.getInstance();
//            int todayYear = calendar.get(Calendar.YEAR);
//            int todayMonth = calendar.get(Calendar.MONTH);
//            int todayDay = calendar.get(Calendar.DAY_OF_MONTH);
//
//            // 今日の日付を強調表示
//            String todayDate = todayYear + "/" + (todayMonth + 1) + "/" + todayDay;
//
//            // CalendarView に今日の日付を強調表示させるために色を変える
//            calendarView.setDate(calendar.getTimeInMillis());
//
//            // 今日の日付の文字色を変更
//            selectDay.setTextColor(Color.parseColor("#0000FF"));  // 例: 今日の日付を青色に変更
//        }

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnNew) {
            Intent intentNew = new Intent(this, New.class);
            startActivity(intentNew);
        }
        if (v.getId() == R.id.btnList) {
            Intent intentList = new Intent(this, List.class);
            startActivity(intentList);
        }
        if (v.getId() == R.id.btnToTop) {
            finish();
        }
    }
}