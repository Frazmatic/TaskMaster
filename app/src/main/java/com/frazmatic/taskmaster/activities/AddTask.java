package com.frazmatic.taskmaster.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.frazmatic.taskmaster.R;

public class AddTask extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
    }

    public void addTask(View view){
        TextView submitted = findViewById(R.id.textSubmittedLabel);
        submitted.setVisibility(View.VISIBLE);
    }

}