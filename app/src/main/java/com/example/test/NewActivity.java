package com.example.test;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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

    EditText title, memo;
    Button btnList, btnToTop;
    Spinner yearSpinner, monthSpinner, daySpinner;
    TodoDbHelper dbHelper;
    SQLiteDatabase db;
    ContentValues values;


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

        dbHelper = new TodoDbHelper(this); //dbHelperの初期化

        yearSpinner = findViewById(R.id.years);
        monthSpinner = findViewById(R.id.months);
        daySpinner = findViewById(R.id.days);

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


        //Listの選択した1件を再編集する
        int todoId = getIntent().getIntExtra("id", -1); //int型の値を取り出す もしキーが見つからなかったら-1を代入

        if (todoId != -1) {
            db = dbHelper.getReadableDatabase();
            Cursor cursor = db.query("todos", null, "id=?",
                    new String[]{String.valueOf(todoId)}, null, null, null);
            if (cursor.moveToFirst()) {
                String editTitle = cursor.getString(cursor.getColumnIndexOrThrow("title"));
                String editMemo = cursor.getString(cursor.getColumnIndexOrThrow("memo"));

                title.setText(editTitle);
                memo.setText(editMemo);

                String[] parts = date.split("/");
                yearSpinner.setSelection(years.indexOf(parts[0]));
                monthSpinner.setSelection(years.indexOf(parts[1]));
                daySpinner.setSelection(years.indexOf(parts[2]));
            }
            cursor.close();
        }
        if (todoId == -1) { //新しいIDなら
            db.insert("todos", null, values); // INSERT（新規登録）
        } else {
            db.update("todos", values, "id = ?", new String[]{String.valueOf(todoId)}); // UPDATE（編集）
        }
        db.close();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.btnList) {

            String titleS = title.getText().toString();
            String memoS = memo.getText().toString();
            String year = yearSpinner.getSelectedItem().toString();
            String month = monthSpinner.getSelectedItem().toString();
            String day = daySpinner.getSelectedItem().toString();
            String dateS = year + "/" + month + "/" + day;

            if (titleS.equals("")) {
                Toast.makeText(this, "タイトルの入力は必須です", Toast.LENGTH_SHORT).show();
            } else {
                SQLiteDatabase db = dbHelper.getWritableDatabase();  //書き込み用のDBを開く

                values = new ContentValues();  //保存する内容をまとめる
                values.put("title", titleS);
                values.put("memo", memoS);
                values.put("date", dateS);
                long newRowId = db.insert("todos", null, values);  //データを保存&追加IDが返ってくる

                if (newRowId != -1) {
                    Toast.makeText(this, "登録されました", Toast.LENGTH_SHORT).show();
                    Intent intentList = new Intent(this, ToDoListActivity.class);
                    startActivity(intentList);

                } else {
                    Toast.makeText(this, "登録に失敗しました", Toast.LENGTH_SHORT).show();
                }
            }
        }
        if (id == R.id.btnToTop) {
            Intent intentToTop = new Intent(this, MainActivity.class);
            startActivity(intentToTop);
        }
    }
}