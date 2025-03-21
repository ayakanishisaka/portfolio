package com.example.todoapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import androidx.appcompat.app.AppCompatActivity;

public class TaskList extends AppCompatActivity implements View.OnClickListener {
    Button newBtnEntry;
    Button btnToTop;
    ListView listView;
    TodoDatabaseHelper dbHelper;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasklist);
        newBtnEntry = findViewById(R.id.btnNew);
        btnToTop = findViewById(R.id.btnToTop);

        newBtnEntry.setOnClickListener(this);
        btnToTop.setOnClickListener(this);

        listView = findViewById(R.id.listView);

        dbHelper = new TodoDatabaseHelper(this);

        // タスク一覧を取得
        cursor = dbHelper.getAllTasks();

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
                android.R.layout.simple_list_item_1, cursor,
                new String[]{TodoDatabaseHelper.COLUMN_TASK},
                new int[]{android.R.id.text1}, 0);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // クリックされたタスクのIDを取得
                cursor.moveToPosition(position);
                int taskId = cursor.getInt(cursor.getColumnIndex(TodoDatabaseHelper.COLUMN_ID));

                // 編集画面に遷移
                Intent intent = new Intent(TaskList.this, EditActivity.class);
                intent.putExtra("TASK_ID", taskId);
                startActivity(intent);
            }
        });
    }


    public void onClick(View v) {
        if (v.getId() == R.id.btnNew) {
            Intent intentBtnNew = new Intent(this, NewActivity.class);
            startActivity(intentBtnNew);
        } else if (v.getId() == R.id.btnToTop) {
            Intent intentToTop = new Intent(this, MainActivity.class);
            startActivity(intentToTop);
        } else {
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // cursorが開いている場合は閉じる
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
    }
}

