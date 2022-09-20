package com.frazmatic.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class AddTask extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
    }

    public void addTask(View view){
        TextView submitted = findViewById(R.id.text_submitted_label);
        submitted.setVisibility(View.VISIBLE);
    }

}