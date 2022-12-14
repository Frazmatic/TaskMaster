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

        TextView titleText = findViewById(R.id.textViewTaskDetailsTitle);
        titleText.setText(intent.getStringExtra("title"));

        TextView detailsText = findViewById(R.id.textViewDetailsBody);
        detailsText.setText(intent.getStringExtra("body"));

        TextView date = findViewById(R.id.textViewDetailsDate);
        String dateText = "Created On" + intent.getStringExtra("date");
        date.setText(dateText);

        TextView state = findViewById(R.id.textViewDetailsState);
        state.setText(intent.getStringExtra("state"));

        TextView team = findViewById(R.id.textViewDetailsTeam);
        String teamText = "Team: " + intent.getStringExtra("team");
        team.setText(teamText);

    }
}