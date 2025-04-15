package com.example.test;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ToDoListActivity extends AppCompatActivity
        implements AdapterView.OnItemClickListener, View.OnClickListener, AdapterView.OnItemLongClickListener {
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
        todoIdList = new ArrayList<>();

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, todoList); //1行のテキストだけ表示
        listView.setAdapter(adapter); //リストの中身をアダプターを使って表示

        listView.setOnItemClickListener(this);
        listView.setOnItemLongClickListener(this);     // 長押しで削除確認ダイアログ
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

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        int todoId = todoIdList.get(position); // IDリストから取得
        String itemText = todoList.get(position);

        showDeleteDialog(todoId, itemText);
        return true; //長押しだけに反応する(true）
    }

    private void showDeleteDialog(int todoId, String itemText) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("削除の確認");
        builder.setMessage("このToDoを削除しますか？\n\n" + itemText);

        builder.setPositiveButton("削除", new DialogInterface.OnClickListener() { //OKのボタン
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.delete("todos", "id = ?", new String[]{String.valueOf(todoId)});
                db.close();
                loadTodos();
                Toast.makeText(ToDoListActivity.this, "削除しました", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("キャンセル", null); //NGのボタン
        builder.show();
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
