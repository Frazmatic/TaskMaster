package com.frazmatic.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences settings;
    private TextView mainTitle;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        settings = PreferenceManager.getDefaultSharedPreferences(this);
        updateUserName();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateUserName();
    }

    private void updateUserName(){
        username = settings.getString(Settings.USERNAME_KEY, "No User Name");
        mainTitle = (TextView)findViewById(R.id.textMainTitle);
        mainTitle.setText(username + "'s Tasks:");
    }

    public void addTask(View view){
        Intent intent = new Intent(this, AddTask.class);
        startActivity(intent);
    }

    public void allTasks(View view){
        Intent intent = new Intent(this, AllTasks.class);
        startActivity(intent);
    }

    public void taskDetails(View view){
        Intent intent = new Intent(this, TaskDetail.class);
        String buttonText = ((Button)view).getText().toString();
        intent.putExtra("taskTitle", buttonText);
        startActivity(intent);
    }

    public void settings(View view){
        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
    }
}