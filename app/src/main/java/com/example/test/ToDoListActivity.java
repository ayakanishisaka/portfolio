package com.example.test;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ToDoListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener {
    Button btnNew, btnToTop;
    TodoDbHelper dbHelper;
    ListView listView;
    ArrayAdapter<String> adapter;
    ArrayList<String> todoList;
    ArrayList<Integer> todoIdList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todolist);

        btnNew = findViewById(R.id.btnNew);
        btnToTop = findViewById(R.id.btnToTop);
        listView = findViewById(R.id.listView);
        todoList = new ArrayList<>(); //データを入れるリスト作成
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, todoList); //1行のテキストだけ表示
        listView.setAdapter(adapter); //リストの中身をアダプターを使って表示
        listView.setOnItemClickListener(this);

        btnNew.setOnClickListener(this);
        btnToTop.setOnClickListener(this);
        dbHelper = new TodoDbHelper(this);

        loadTodos();
    }

    private void loadTodos() {
        SQLiteDatabase db = dbHelper.getReadableDatabase(); //読み取り専用DBを開く
        todoList.clear(); //リストを一度クリアする（再読み込み対策）
        todoIdList.clear();

        Cursor cursor = db.query("todos", null, null, null, null, null, "date ASC", null);
        while (cursor.moveToNext()) { //次に行があるか
            int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
            String date = cursor.getString(cursor.getColumnIndexOrThrow("date"));
            String title = cursor.getString(cursor.getColumnIndexOrThrow("title"));
            String memo = cursor.getString(cursor.getColumnIndexOrThrow("memo"));
            todoList.add("● " + date + " : " + title + "  (" + memo + ")"); //表示用にデータをまとめる
            todoIdList.add(id);
        }

        cursor.close();
        db.close();

        adapter.notifyDataSetChanged(); //データの変更を伝える
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        int todoId = todoIdList.get(position); //押されたIDを取得
        Intent intent = new Intent(this, NewActivity.class);
        intent.putExtra("id", todoId); // IDだけを渡す
        startActivity(intent);
    }

    public void onClick(View v) {
        if (v.getId() == R.id.btnNew) {
            Intent intentNew = new Intent(this, NewActivity.class);
            startActivity(intentNew);
        }
        if (v.getId() == R.id.btnToTop) {
            Intent intentToTop = new Intent(this, MainActivity.class);
            startActivity(intentToTop);
        }
    }
}
