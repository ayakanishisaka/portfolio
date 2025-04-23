package com.example.test;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class NewActivity extends AppCompatActivity implements View.OnClickListener {

    EditText title, memo;
    Button btnList, btnToTop;
    Spinner yearSpinner, monthSpinner, daySpinner;
    ArrayList<String> years, months, days;
    TodoDbHelper dbHelper;
    SQLiteDatabase db;
    ContentValues values;
    CalendarView calendarView;
    Calendar c;
    int todoId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        title = findViewById(R.id.title);
        memo = findViewById(R.id.memo);
        btnList = findViewById(R.id.btnList);
        btnToTop = findViewById(R.id.btnToTop);
        yearSpinner = findViewById(R.id.years);
        monthSpinner = findViewById(R.id.months);
        daySpinner = findViewById(R.id.days);

        btnList.setOnClickListener(this);
        btnToTop.setOnClickListener(this);


        spinners();

        dbHelper = new TodoDbHelper(this); // dbHelperの初期化

        //一覧画面のリストから選択した1件を再編集または新規登録かを判断する
        todoId = getIntent().getIntExtra("id", -1);// ID(int)情報を取得(もしキーがなければ-1)

        if (todoId != -1) { // IDがあるなら再編集
            db = dbHelper.getReadableDatabase();
            Cursor cursor = db.query("todos", null, "id=?",
                    new String[]{String.valueOf(todoId)}, null, null, null);
            if (cursor.moveToFirst()) {
                String editTitle = cursor.getString(cursor.getColumnIndexOrThrow("title"));
                String editMemo = cursor.getString(cursor.getColumnIndexOrThrow("memo"));

                title.setText(editTitle);
                memo.setText(editMemo);

                String dateString = cursor.getString(cursor.getColumnIndexOrThrow("date"));
                String[] parts = dateString.split("/");
                yearSpinner.setSelection(years.indexOf(parts[0]));
                monthSpinner.setSelection(months.indexOf(parts[1]));
                daySpinner.setSelection(days.indexOf(parts[2]));
            }
            cursor.close();
            db.close();

        } else {
            // MainActivityのCalendarViewから渡された日付があるか確認
            Intent intentDate = getIntent();
            String passedYear = intentDate.getStringExtra("year");
            String passedMonth = intentDate.getStringExtra("month");
            String passedDay = intentDate.getStringExtra("day");

            int year, month, day;

            if (passedYear != null && passedMonth != null && passedDay != null) {
                year = Integer.parseInt(passedYear);
                month = Integer.parseInt(passedMonth);
                day = Integer.parseInt(passedDay);
            } else {
                // 渡されていなければ現在日時を使う
                c = Calendar.getInstance();
                year = c.get(Calendar.YEAR);
                month = c.get(Calendar.MONTH) + 1;
                day = c.get(Calendar.DAY_OF_MONTH);
            }
            // ToDo 日時を取得してセット
            yearSpinner.setSelection(years.indexOf(String.valueOf(year)));
            monthSpinner.setSelection(months.indexOf(String.format("%02d", month)));
            daySpinner.setSelection(days.indexOf(String.format("%02d", day)));
        }
    }

    private void spinners() {
        years = new ArrayList<>();
        months = new ArrayList<>();
        days = new ArrayList<>();

        for (int i = 2025; i <= 2035; i++) {
            years.add(String.valueOf(i));
        }
        for (int i = 1; i <= 12; i++) {
            months.add(String.format("%02d", i));
        }
        for (int i = 1; i <= 31; i++) {
            days.add(String.format("%02d", i));
        }

        //年・月・日の各スピナーにセットする
        spinnerAdapter(yearSpinner, years);
        spinnerAdapter(monthSpinner, months);
        spinnerAdapter(daySpinner, days);
    }

    // アダプター（配列をスピナーに接続するもの）を使ってスピナーに選択肢を設定
    private void spinnerAdapter(Spinner spinner, List<String> data) {
        // 現在の Context（このActivity）にアダプターを使ってdataをString型で一行表示する
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, data);
        // アダプターにドロップダウンを設定
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // 受け取ったスピナーに上記のレイアウトがあるアダプターをセットして表示
        spinner.setAdapter(adapter);
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

            // Todo 日付の入力が正しい値かチェック　NGならreturn
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            // 厳密なチェックをしたいときはfalse（デフォルト(勝手に修正)はtrue）
            sdf.setLenient(false);

            try {
                Date date = sdf.parse(dateS); // ← 2月30日は存在しない
            } catch (ParseException e) {
                Toast.makeText(this, "存在しない日付です", Toast.LENGTH_SHORT).show();
                return;
            }

            if (titleS.equals("")) {
                Toast.makeText(this, "タイトルの入力は必須です", Toast.LENGTH_SHORT).show();
            } else {
                SQLiteDatabase db = dbHelper.getWritableDatabase(); // 書き込み用のDBを開く

                values = new ContentValues(); // 保存する内容をまとめる
                values.put("title", titleS);
                values.put("memo", memoS);
                values.put("date", dateS);

                if (todoId != -1) { // IDがあれば更新
                    int upId = db.update("todos", values, "id = ?", new String[]{String.valueOf(todoId)});
                    if (upId > 0) {
                        Toast.makeText(this, "更新されました", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "更新に失敗しました", Toast.LENGTH_SHORT).show();
                    }
                } else { // 更新するIDがなかったら(-1のまま）新規登録
                    long newId = db.insert("todos", null, values); //新規登録
                    if (newId != -1) {
                        Toast.makeText(this, "登録されました", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "登録に失敗しました", Toast.LENGTH_SHORT).show();
                    }
                }
                // 一覧に戻る
                Intent intentList = new Intent(this, ToDoListActivity.class);
                startActivity(intentList);
                finish();
            }
        }
        if (id == R.id.btnToTop) {
            Intent intentToTop = new Intent(this, MainActivity.class);
            startActivity(intentToTop);
            finish();
        }
    }
}
