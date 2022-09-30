package com.frazmatic.taskmaster.activities;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.frazmatic.taskmaster.R;
import com.frazmatic.taskmaster.models.Task;

import java.util.List;

public class AddTask extends AppCompatActivity {
    private int tasksAdded;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        createTaskDatabase();
        tasksAdded = 0;
    }

    public void addTask(View view){
        EditText title = findViewById(R.id.textInputTaskTitle);
        EditText description = findViewById(R.id.textInputTaskDescription);
        if(title.getText().length() > 1){
            Task newTask = new Task(title.getText().toString(), description.getText().toString());
            //TO DO - Add to AWS DynamoDB
            //taskDB.taskDao().insertTask(newTask);

            tasksAdded++;
            TextView taskAddedCount = findViewById(R.id.textViewSubmittedCount);
            taskAddedCount.setText("Tasks Submitted: " + tasksAdded);
            Toast.makeText(AddTask.this, "Task Submitted", Toast.LENGTH_SHORT).show();
            title.setText("");
            description.setText("");
        }

    }

    private void createTaskDatabase(){
        //taskDB = Room.databaseBuilder(getApplicationContext(), TaskDatabase.class, MainActivity.DATABASE_NAME)
                //.allowMainThreadQueries()
                //.fallbackToDestructiveMigration()
                //.build();
    }

}