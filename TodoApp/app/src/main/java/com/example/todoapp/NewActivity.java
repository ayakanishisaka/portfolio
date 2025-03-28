package com.example.todoapp;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class NewActivity extends AppCompatActivity implements View.OnClickListener {

    private TodoRepository todoRepository;
    Button newBtnEntry;
    Button btnToTop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
        todoRepository = new TodoRepository(this);

        newBtnEntry = findViewById(R.id.newBtnEntry);
        btnToTop = findViewById(R.id.btnToTop);

        newBtnEntry.setOnClickListener(this);
        btnToTop.setOnClickListener(this);

        // スピナーをレイアウトから取得
        Spinner years = findViewById(R.id.years);
        Spinner months = findViewById(R.id.months);
        Spinner days = findViewById(R.id.days);

        // DateSpinner のインスタンスを作成
        DateSpinner dateSpinner = new DateSpinner(years, months, days);

        // スピナーに年、月、日を設定
        dateSpinner.setUpSpinners(this);

        // ボタンが押された時の処理
        Button button = findViewById(R.id.button_get_date);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 年月日を取得して表示
                String selectedDate = years.getSelectedDate();
            }
        });
    }

    public void onClick(View v) {
        if (v.getId() == R.id.newBtnEntry) {
            Spinner years = findViewById(R.id.years);
            Spinner months = findViewById(R.id.months);
            Spinner days = findViewById(R.id.days);
            EditText toDoTitleEditText = findViewById(R.id.toDoTitle);
            EditText subTitle1EditText = findViewById(R.id.subTitle1);
            EditText subTitle2EditText = findViewById(R.id.subTitle2);
            EditText subTitle3EditText = findViewById(R.id.subTitle3);
            EditText deadLineEditText = findViewById(R.id.deadLine);
            EditText memoEditText = findViewById(R.id.memo);

            String year = years.getSelectedItem().toString();
            String month = months.getSelectedItem().toString();
            String day = days.getSelectedItem().toString();
            String toDoTitle = toDoTitleEditText.getText().toString();
            String subTitle1 = subTitle1EditText.getText().toString();
            String subTitle2 = subTitle2EditText.getText().toString();
            String subTitle3 = subTitle3EditText.getText().toString();
            String deadline = deadLineEditText.getText().toString();
            String memo = memoEditText.getText().toString();

            todoRepository.addTodo(toDoTitle, subTitle1, subTitle2, subTitle3, deadline, memo);
            finish();  // 新規TODOを追加した後、画面を閉じる
            Intent intentNewEntry = new Intent(this, ListActivity.class);
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