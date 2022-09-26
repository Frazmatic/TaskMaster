package com.frazmatic.taskmaster.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.frazmatic.taskmaster.R;
import com.frazmatic.taskmaster.activities.database.TaskDatabase;
import com.frazmatic.taskmaster.adapters.TaskAdapter;
import com.frazmatic.taskmaster.models.Task;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences settings;
    private TextView mainTitle;
    private String username;
    public static final String DATABASE_NAME = "task_db";
    private TaskDatabase taskDB;
    private List<Task> tasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        settings = PreferenceManager.getDefaultSharedPreferences(this);
        updateUserName();
        createTaskDatabase();
        recyclerSetup();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateUserName();
        tasks.clear();
        tasks.addAll(taskDB.taskDao().findAll());
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

    public void settings(View view){
        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
    }

    private  void recyclerSetup(){
        RecyclerView taskRecycler = findViewById(R.id.recyclerTasks);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        taskRecycler.setLayoutManager(layoutManager);
        TaskAdapter adapter = new TaskAdapter(tasks, this);
        taskRecycler.setAdapter(adapter);
    }

    private void createTaskDatabase(){
        taskDB = Room.databaseBuilder(getApplicationContext(), TaskDatabase.class, DATABASE_NAME)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
        tasks = taskDB.taskDao().findAll();
    }

}