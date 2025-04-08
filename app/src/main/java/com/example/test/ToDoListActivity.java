package com.example.test;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ToDoListActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnNew;
    Button btnToTop;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todolist);

        btnNew = findViewById(R.id.btnNew);
        btnToTop = findViewById(R.id.btnToTop);

        btnNew.setOnClickListener(this);
        btnToTop.setOnClickListener(this);

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
