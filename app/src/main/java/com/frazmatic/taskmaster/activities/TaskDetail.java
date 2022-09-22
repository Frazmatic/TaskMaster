package com.frazmatic.taskmaster.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.frazmatic.taskmaster.R;

public class TaskDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);

        Intent intent = getIntent();
        String title = intent.getStringExtra("taskTitle");
        String body = intent.getStringExtra("taskBody");

        TextView titleText = findViewById(R.id.textViewTaskDetailsTitle);
        titleText.setText(title);

        TextView detailsText = findViewById(R.id.textViewDetailsBody);
        detailsText.setText(body);
    }
}