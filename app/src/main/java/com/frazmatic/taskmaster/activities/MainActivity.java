package com.frazmatic.taskmaster.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.frazmatic.taskmaster.R;
import com.frazmatic.taskmaster.adapters.TaskAdapter;
import com.frazmatic.taskmaster.models.Task;

import java.util.ArrayList;
import java.util.List;

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
        recyclerSetup();
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

    public void settings(View view){
        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
    }

    private  void recyclerSetup(){
        RecyclerView taskRecycler = findViewById(R.id.recyclerTasks);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        taskRecycler.setLayoutManager(layoutManager);
        TaskAdapter adapter = new TaskAdapter(getSampleTaskList(), this);
        taskRecycler.setAdapter(adapter);
    }

    private List<Task> getSampleTaskList(){
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Task("Book Dental Appointment", "ouch my tooth hurts"));
        tasks.add(new Task("Debug this program", "this could take a while"));
        tasks.add(new Task("Do Taxes", "OMG they're 6 months late"));
        return tasks;
    }
}