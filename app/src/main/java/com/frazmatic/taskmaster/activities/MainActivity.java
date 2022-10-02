package com.frazmatic.taskmaster.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Task;
import com.frazmatic.taskmaster.R;
import com.frazmatic.taskmaster.adapters.TaskAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String main_tag ="Main Activity";
    private SharedPreferences settings;
    private List<Task> tasks;
    private TaskAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        settings = PreferenceManager.getDefaultSharedPreferences(this);
        updateUserName();
        tasks = new ArrayList<>();
        recyclerSetup();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateUserName();
        loadTasksFromAmplify();
    }

    private void updateUserName(){
        String username = settings.getString(Settings.USERNAME_KEY, "No User Name");
        TextView mainTitle = findViewById(R.id.textMainTitle);
        String title = username + "'s Tasks:";
        mainTitle.setText(title);
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
        adapter = new TaskAdapter(tasks, this);
        taskRecycler.setAdapter(adapter);
    }

    private void loadTasksFromAmplify(){
        Amplify.API.query(
            ModelQuery.list(Task.class),
            success -> {
                Log.i(main_tag, "Loaded Tasks from Amplify");
                tasks.clear();
                for (Task t : success.getData()){
                    tasks.add(t);
                }
                runOnUiThread(() ->
                        adapter.notifyDataSetChanged()
                );
            },
            failure -> Log.i(main_tag,"Failed to Load task from Amplify: " + failure.getMessage())
        );
    }
}